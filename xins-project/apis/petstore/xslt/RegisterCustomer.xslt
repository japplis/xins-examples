<?xml version="1.0" encoding="UTF-8"?>
<!--
 $Id: RegisterCustomer.xslt,v 1.5 2006/04/06 10:56:31 agoubard Exp $
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:include href="Commons.xslt" />

	<xsl:template match="commandresult">
		<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
			<xsl:call-template name="header">
				<xsl:with-param name="title" select="'Create a new account'" />
			</xsl:call-template>
			<body>
				<xsl:call-template name="page-header">
					<xsl:with-param name="title" select="'Create a new account'" />
				</xsl:call-template>
				<div id="content">
					<xsl:call-template name="error" />
					<form method="{$form-method}" action="{$application-url}">
						<input type="hidden" name="command" value="{$command}" />
						<input type="hidden" name="action" value="Okay" />
						<table id="content">
							<tr>
								<td id="label">E-mail:</td>
								<td><input name="email" type="text" id="email" value="{parameter[@name='input.email']}" /></td>
							</tr>
							<tr>
								<td id="label">Password:</td>
								<td><input name="password" type="password" id="password" /></td>
							</tr>
							<tr>
								<td id="label">Gender:</td>
								<td>
									<select name="salutation" id="salutation">
										<option value="Mister">Mister</option>
										<option value="Madam">Miss</option>
									</select>
								</td>
							</tr>
							<tr>
								<td id="label">First name:</td>
								<td><input name="firstName" type="text" id="firstName" value="{parameter[@name='input.firstName']}" /></td>
							</tr>
							<tr>
								<td id="label">Last name:</td>
								<td><input name="lastName" type="text" id="lastName" value="{parameter[@name='input.lastName']}" /></td>
							</tr>
							<tr>
								<td id="label">Address:</td>
								<td><input name="address" type="text" id="address" value="{parameter[@name='input.address']}" /></td>
							</tr>
							<tr>
								<td id="label">Phone number:</td>
								<td><input name="phoneNumber" type="text" id="phoneNumber" value="{parameter[@name='input.phoneNumber']}" /></td>
							</tr>
							<tr>
								<td colspan="2" id="submit"><input id="submit" type="submit" value="Register" /></td>
							</tr>
						</table>
					</form>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>