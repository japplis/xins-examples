#!/usr/bin/perl

use strict;
use warnings;

use XML::XPath;
use XML::XPath::XMLParser;

use LWP::UserAgent;

# Create a user agent object
my $ua = LWP::UserAgent->new;

# Create a request
my $req = HTTP::Request->new(POST => 'http://localhost:8080/myproject/');
$req->content_type('application/x-www-form-urlencoded');
$req->content('_function=MyFunction&gender=m&personLastName=Bean');

# Pass request to the user agent and get a response back
my $res = $ua->request($req);

# Check the outcome of the response
die "Unable to connect\n" unless $res->is_success;

# Create a new XPath parser object
my $xp = XML::XPath->new(xml => $res->content);

# Check for errors
my $errorCode = $xp->find('/result/@errorcode');
die "Error returned: $errorCode\n" if $errorCode;

# Retrieve and print the message
my $message = $xp->find('/result/param[@name="message"]');
print "$message\n";
