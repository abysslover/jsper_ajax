<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="webmethods">
		<html>
			<head>
				<title>Web Service Operations</title>
				<script language="Javascript">
				<![CDATA[
					function invoke(name) {
						var form = document.getElementById(name+'Form');
						form.action = "HelloWebInvoker.jsp";
						form.submit();
					}
				]]>
				</script>
			</head>
			<body>
				<table>
					<tr>
						<th>메서드명</th>
						<th>반환 형태</th>
						<th>파라미터 형태</th>
						<th>실행</th>
					</tr>
					<xsl:apply-templates select="method" />
				</table>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="method">
		<tr>
			<td><xsl:value-of select="@name" /></td>
			<td><xsl:value-of select="returnType" /></td>
			<td>
				<xsl:for-each select="*">
					<xsl:if test="contains(name(), 'param')">
						<xsl:value-of select="." />
						<xsl:if test="position() != last()">,</xsl:if>
					</xsl:if>
				</xsl:for-each>
			</td>
			<td>
				<form id="{@name}Form">
					<xsl:for-each select="*[contains(name(), 'param')]">
						<input name="{name()}" type="text" />
					</xsl:for-each>
					<input name="method" type="hidden" value="{@name}" />
					<input type="button" value="실행"
						onclick="invoke('{@name}');" />
				</form>
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>