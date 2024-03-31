<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:tns="http://www.example.org/HelloXSD"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.example.org/HelloXSD ../xsd/HelloXSD.xsd">
	<xsl:template match="/">
		<html>
			<div>Hello<xsl:value-of select="." /></div>
			<table border="1">
				<tr>
					<th>나이</th>
					<th>생일</th>
					<th>이름</th>
				</tr>
				<tr>
					<td><xsl:value-of select="*/@age" /></td>
					<td><xsl:value-of select="*/@birth" /></td>
					<td><xsl:value-of select="*/@name" /></td>
				</tr>
				<tr>
					<td colspan="3">
						<xsl:apply-templates select="*/tns:address" />
					</td>
				</tr>
			</table>
		</html>
	</xsl:template>
	<xsl:template match="tns:address">
		<xsl:call-template name="makeInformation">
			<xsl:with-param name="infoName" select="'국적'" />
			<xsl:with-param name="infoValue" select="tns:nation/tns:item[1]/@label" />
		</xsl:call-template>
		<div><xsl:for-each select="./*">
				<xsl:value-of select="concat(' ', ./*/@label)" />
			</xsl:for-each></div>
	</xsl:template>
	<xsl:template name="makeInformation">
		<xsl:param name="infoName" />
		<xsl:param name="infoValue" />
		<div>당신의 <xsl:value-of select="$infoName" />은(는) <xsl:value-of select="$infoValue" /> 입니다.</div>
	</xsl:template>
</xsl:stylesheet>