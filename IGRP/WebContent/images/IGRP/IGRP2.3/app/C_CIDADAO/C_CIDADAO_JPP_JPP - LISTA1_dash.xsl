<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="html" omit-xml-declaration="yes" encoding="ISO-8859-1" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
  <xsl:template match="/">
    <html><!--XSL Generator (RED / DIM )--><!--HOME-HEADER--><xsl:call-template name="home-header"/>
      <xsl:apply-templates mode="js_validation" select="rows/content/js_validation"/>
      <body><!--HOME TOP--><xsl:call-template name="home-top-main"/><!--CENTER PANE--><div class="general">
          <div id="content">
            <div class="ui-layout-center">
              <xsl:call-template name="dialog-modal"/><!-- START YOUR CODE HERE --><div class="box-content"><!--PAGE TITLE--><xsl:call-template name="page-title">
                  <xsl:with-param name="title" select="rows/content/title"/>
                </xsl:call-template><!--END PAGE TITLE--><!-- START NOTIFICATION MESSAGES--><xsl:apply-templates mode="notif_messages" select="rows/content/messages">
                  <xsl:with-param name="class" select="'notification'"/>
                </xsl:apply-templates><!-- END NOTIFICATION MESSAGES--><form action="#" method="post" id="formular_default" name="formular_default" class="default_filter"><!-- START HIDDEN FILTER--><xsl:apply-templates mode="form-hidden" select="rows/content/filter/value"/><!-- END HIDDEN FILTER--><!-- START HIDDEN FORM--><xsl:apply-templates mode="form-hidden" select="rows/content/form/value"/><!-- END HIDDEN FORM--><!-- START FILTER--><div class="box-content">
                    <xsl:if test="rows/content/filter/label/min">
                      <label>
                        <span>
                          <xsl:value-of select="rows/content/filter/label/min"/>
                        </span>
                        <select name="{rows/content/filter/list/min/@name}" class="selectchange IGRP_change" maxlength="30">
                          <xsl:call-template name="FIELD_validator">
                            <xsl:with-param name="field" select="rows/content/filter/label/min"/>
                          </xsl:call-template>
                          <xsl:for-each select="rows/content/filter/list/min/option">
                            <option value="{value}">
                              <xsl:if test="@selected='true'">
                                <xsl:attribute name="selected">selected</xsl:attribute>
                              </xsl:if>
                              <xsl:value-of select="text"/>
                            </option>
                          </xsl:for-each>
                        </select>
                        <a id="idp_min"/>
                      </label>
                    </xsl:if>
                    <xsl:if test="rows/content/filter/label/cc">
                      <label>
                        <span>
                          <xsl:value-of select="rows/content/filter/label/cc"/>
                        </span>
                        <input type="LOOKUP" name="{rows/content/filter/value/cc/@name}" value="{rows/content/filter/value/cc}" class="LOOKUP" maxlength="30">
                          <xsl:call-template name="FIELD_validator">
                            <xsl:with-param name="field" select="rows/content/filter/label/cc"/>
                          </xsl:call-template>
                        </input>
                        <xsl:call-template name="lookup-tool">
                          <xsl:with-param name="page" select="rows/page"/>
                          <xsl:with-param name="ad_hoc" select="'1'"/>
                          <xsl:with-param name="action" select="'LOOKUP'"/>
                          <xsl:with-param name="name" select="rows/content/filter/value/cc/@name"/>
                          <xsl:with-param name="js_lookup" select="rows/content/filter/lookup/cc"/>
                        </xsl:call-template>
                      </label>
                    </xsl:if><!--START TOOL BAR FILTER--><xsl:apply-templates mode="button-bar" select="rows/content/filter/tools-bar"/><!--END TOOL BAR FILTER--></div><!-- END FILTER--><!--START TOOL BAR TABLE--><xsl:apply-templates mode="tools-bar" select="rows/content/table/tools-bar"/><!--END TOOL BAR TABLE--><!-- START TABELA--><!--  CONTEXT-MENU  --><xsl:apply-templates mode="context-menu" select="rows/content/table/context-menu"/>
                  <div class="table table-responsive">
                    <table class="sortable IGRP_table">
                      <thead>
                        <tr>
                          <xsl:if test="rows/content/table/label/ministerio">
                            <th align="right">
                              <xsl:value-of select="rows/content/table/label/ministerio"/>
                            </th>
                          </xsl:if>
                          <xsl:if test="rows/content/table/label/centro_custo">
                            <th align="right">
                              <xsl:value-of select="rows/content/table/label/centro_custo"/>
                            </th>
                          </xsl:if>
                        </tr>
                      </thead>
                      <tbody>
                        <xsl:for-each select="rows/content/table/value/row[not(@total='yes')]">
                          <xsl:variable name="pos" select="(position()+1) mod 2"/>
                          <tr class="IGRP_contextMenu">
                            <xsl:apply-templates mode="context-param" select="context-menu"/>
                            <xsl:if test="ministerio">
                              <td align="right" class="table_row{$pos}" data-title="{../../label/ministerio}">
                                <xsl:value-of select="ministerio"/>
                              </td>
                            </xsl:if>
                            <xsl:if test="centro_custo">
                              <td align="right" class="table_row{$pos}" data-title="{../../label/centro_custo}">
                                <xsl:value-of select="centro_custo"/>
                              </td>
                            </xsl:if>
                          </tr>
                        </xsl:for-each>
                      </tbody>
                      <tfoot>
                        <tr>
                          <xsl:if test="rows/content/table/label/ministerio">
                            <td align="right">
                              <xsl:value-of select="rows/content/table/value/row[@total='yes']/ministerio"/>
                            </td>
                          </xsl:if>
                          <xsl:if test="rows/content/table/label/centro_custo">
                            <td align="right">
                              <xsl:value-of select="rows/content/table/value/row[@total='yes']/centro_custo"/>
                            </td>
                          </xsl:if>
                        </tr>
                      </tfoot>
                    </table>
                  </div><!-- END TABELA--></form><!-- END YOUR CODE HERE --></div>
            </div><!-- START HOME BOTTOM --><xsl:call-template name="home-side"/>
          </div>
        </div><!--HOME-FOOTER--></body>
    </html>
  </xsl:template>
  <xsl:include href="../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=20141031"/>
  <xsl:include href="../../xsl/tmpl/IGRP-JSvalidation.tmpl.xsl?v=20141031"/>
  <xsl:include href="../../xsl/tmpl/IGRP-field-validator.tmpl.xsl?v=20141031"/>
  <xsl:include href="../../xsl/tmpl/IGRP-others.tmpl.xsl?v=20141031"/>
</xsl:stylesheet>