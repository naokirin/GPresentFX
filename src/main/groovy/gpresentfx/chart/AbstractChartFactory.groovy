package main.groovy.gpresentfx.chart

import javafx.scene.chart.Chart
import main.groovy.gpresentfx.GPresentBuilder
import javafx.geometry.Side
import main.groovy.gpresentfx.AbstractRegionFactory

abstract class AbstractChartFactory extends AbstractRegionFactory {
  static final String titleKeyword = 'title'
  static final String titleSideKeyword = 'titleside'
  static final String chartDataKeyword = 'data'

  protected static void setTitle(Chart chart, Map attribute, GPresentBuilder pdb){
    if(attribute[titleKeyword] != null)
      chart.setTitle(attribute[titleKeyword].toString())
    if(attribute[titleSideKeyword] != null)
      chart.setTitleSide(Side.valueOf(attribute[titleSideKeyword].toString().toUpperCase()))
  }
}
