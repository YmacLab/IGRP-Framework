<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="html" omit-xml-declaration="yes" encoding="ISO-8859-1" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
  <xsl:template match="/">
    <html><!--XSL Generator (RED/DIM 2015)--><head><!--HOME-HEADER--><xsl:call-template name="home-header"/>
      </head>
      <body class="bodyColor">
        <div class="IGRP_overlay"/><!--HOME TOP--><xsl:call-template name="home-top-main"/><!--HOME--><div id="igrp-bodyPage"><!--HOME LEFT--><xsl:if test="not(rows/target) or rows/target!='_blank'"><!--MENU--><div class="bodyPageLeft">
              <xsl:apply-templates mode="slide-menu" select="document(rows/slide-menu/@file)"/>
            </div>
          </xsl:if><!--HOME RIGTH--><div class="bodyPageRigth"><!-- START YOUR CODE HERE --><!-- SHOW TABLET AND MOBILE TITLE --><div class="box-head showTabMobile">
              <xsl:value-of select="rows/content/title"/>
            </div><!-- SHOW NOTIFICATION MESSAGES--><xsl:apply-templates mode="notif_messages" select="rows/content/messages"/><!-- START VIEW --><xsl:apply-templates mode="IGRP-view" select="rows/content/view"/><!-- END VIEW --><!-- START TAB MENU  --><xsl:apply-templates mode="tab-menu" select="rows/content/menu"/><!-- END TAB MENU  --><form action="#" method="post" id="formular_default" name="formular_default" class="default_form" enctype="multipart/form-data"><!-- SHOW HIDDEN--><xsl:apply-templates mode="form-hidden" select="rows/content/filter/value"/>
              <xsl:apply-templates mode="form-hidden" select="rows/content/form/value"/><!-- START FORM--><div class="col addMarginTop" sep-name="form"><!-- INICIO SEPARADOR legenda--><xsl:if test="rows/content/form/label/legenda">
                  <div class="box-content resetPadding" list-name="legenda">
                    <xsl:call-template name="titlecollapser">
                      <xsl:with-param name="title" select="rows/content/form/label/legenda"/>
                    </xsl:call-template>
                    <div class="box-collapser"><!-- START TABELA LIST--><div class="box-table table-responsive">
                        <table rel="T_legenda" data-control="data-legenda">
                          <thead>
                            <tr>
                              <xsl:if test="rows/content/form/table/legenda/label/imagem">
                                <xsl:if test="not(rows/content/form/table/legenda/label/imagem/@visible)">
                                  <th>
                                    <xsl:value-of select="rows/content/form/table/legenda/label/imagem" disable-output-escaping="yes"/>
                                  </th>
                                </xsl:if>
                              </xsl:if>
                              <xsl:if test="rows/content/form/table/legenda/label/titulo">
                                <xsl:if test="not(rows/content/form/table/legenda/label/titulo/@visible)">
                                  <th>
                                    <xsl:value-of select="rows/content/form/table/legenda/label/titulo" disable-output-escaping="yes"/>
                                  </th>
                                </xsl:if>
                              </xsl:if>
                            </tr>
                          </thead>
                          <tbody>
                            <xsl:for-each select="rows/content/form/table/legenda/value/row[not(@total='yes')]">
                              <tr row="{position()}">
                                <xsl:if test="imagem">
                                  <xsl:if test="not(imagem/@visible)">
                                    <td data-row="{position()}" data-title="{../../label/imagem}" class="text">
                                      <xsl:value-of select="imagem" disable-output-escaping="yes"/>
                                      <input type="hidden" name="p_imagem_fk" value="{imagem}"/>
                                      <span class="showResponsiveTable ctxmenu"/>
                                    </td>
                                  </xsl:if>
                                  <xsl:if test="imagem/@visible = 'false'">
                                    <input type="hidden" name="p_imagem_fk" value="{imagem}"/>
                                  </xsl:if>
                                </xsl:if>
                                <xsl:if test="titulo">
                                  <xsl:if test="not(titulo/@visible)">
                                    <td data-row="{position()}" data-title="{../../label/titulo}" class="text">
                                      <xsl:value-of select="titulo" disable-output-escaping="yes"/>
                                      <input type="hidden" name="p_titulo_fk" value="{titulo}"/>
                                    </td>
                                  </xsl:if>
                                  <xsl:if test="titulo/@visible = 'false'">
                                    <input type="hidden" name="p_titulo_fk" value="{titulo}"/>
                                  </xsl:if>
                                </xsl:if>
                                <input type="hidden" name="p_legenda_id" value="{legenda_id}"/>
                              </tr>
                            </xsl:for-each>
                          </tbody>
                        </table>
                      </div><!-- END TABELA LIST--><div class="_clear"/>
                    </div>
                    <div class="_clear"/>
                  </div>
                </xsl:if>
                <div class="_clear"/>
              </div><!-- START FILTER--><div class="box-content resetPadding">
                <div class="col">
                  <xsl:if test="rows/content/filter/label/pesquisa">
                    <div class="col-1-4 item" item-name="pesquisa">
                      <div class="igrp_item text">
                        <label>
                          <xsl:value-of select="rows/content/filter/label/pesquisa" disable-output-escaping="yes"/>
                        </label>
                        <input type="text" name="{rows/content/filter/value/pesquisa/@name}" value="{rows/content/filter/value/pesquisa}" class="text" maxlength="30">
                          <xsl:call-template name="FIELD_validator">
                            <xsl:with-param name="field" select="rows/content/filter/label/pesquisa"/>
                          </xsl:call-template>
                        </input>
                        <div class="_clear"/>
                      </div>
                      <div class="_clear"/>
                    </div>
                  </xsl:if>
                  <div class="_clear"/>
                </div><!--START TOOL BAR FILTER--><xsl:apply-templates mode="button-bar" select="rows/content/filter/tools-bar"/><!--END TOOL BAR FILTER--></div><!-- END FILTER--><!-- START TABELA--><div class="col addBorderLeftRight borderColor">
                <div class="box-table table-responsive">
                  <div class="table-container">
                    <table class="IGRP_table default-table" data-control="table">
                      <thead>
                        <tr>
                          <xsl:if test="rows/content/table/label/resultado">
                            <th align="">
                              <xsl:value-of select="rows/content/table/label/resultado"/>
                            </th>
                          </xsl:if>
                        </tr>
                      </thead>
                      <tbody>
                        <xsl:for-each select="rows/content/table/value/row[not(@total='yes')]">
                          <tr row="{position()}" class="">
                            <xsl:apply-templates mode="context-param" select="context-menu"/>
                            <xsl:if test="resultado">
                              <td align="" data-row="{position()}" data-title="{../../label/resultado}" class="text">
                                <xsl:value-of select="resultado"/>
                              </td>
                            </xsl:if>
                          </tr>
                        </xsl:for-each>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div><!-- END TABELA--></form><!-- END YOUR CODE HERE --><div class="_clear"/>
          </div>
          <div class="_clear"/>
        </div><!--FOOTER PAGE--><div id="igrp-footerPage">
          <xsl:call-template name="footer"/>
        </div>
      </body>
    </html>
  </xsl:template>
  <xsl:include href="../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=20150309"/>
  <xsl:include href="../../xsl/tmpl/IGRP-setAttribute.tmpl.xsl?v=20150309"/>
  <xsl:include href="../../xsl/tmpl/IGRP-slide-menu.tmpl.xsl?v=20150309"/>
  <xsl:include href="../../xsl/tmpl/IGRP-view.tmpl.xsl?v=20150309"/>
  <xsl:include href="../../xsl/tmpl/IGRP-tab-menu.tmpl.xsl?v=20150309"/>
</xsl:stylesheet>