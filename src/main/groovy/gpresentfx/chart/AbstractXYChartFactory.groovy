package main.groovy.gpresentfx.chart

import javafx.scene.chart.Axis
import javafx.scene.chart.CategoryAxis
import javafx.scene.chart.NumberAxis

class AbstractXYChartFactory extends AbstractChartFactory{
  static final String xAxisCategoryKeyword = 'xcategory'
  static final String yAxisCategoryKeyword = 'ycategory'
  static final String xTitleKeyword = 'xtitle'
  static final String yTitleKeyword = 'ytitle'
  static final String xRangeKeyword = 'xrange'
  static final String yRangeKeyword = 'yrange'
  static final String xAutoRangeKeyword = 'xautorange'
  static final String yAutoRangeKeyword = 'yautorange'
  static final String xTickUnitKeyword = 'xunit'
  static final String yTickUnitKeyword = 'yunit'

  private static void setXRange(Axis axis, Map attribute){
    if(axis instanceof NumberAxis){
      if(attribute[xRangeKeyword] != null){
        axis.setLowerBound((double)attribute[xRangeKeyword][0])
        axis.setUpperBound((double)attribute[xRangeKeyword][1])
        axis.setAutoRanging(false)
      }
    }
  }

  private static void setYRange(Axis axis, Map attribute){
    if(axis instanceof NumberAxis){
      if(attribute[yRangeKeyword] != null){
        axis.setLowerBound((double)attribute[yRangeKeyword][0])
        axis.setUpperBound((double)attribute[yRangeKeyword][1])
        axis.setAutoRanging(false)
      }
    }
  }

  private static void setXTickUnit(Axis axis, Map attribute){
    if(axis instanceof NumberAxis){
      if(attribute[xTickUnitKeyword] != null)
        axis.setTickUnit((double)attribute[xTickUnitKeyword])
    }
  }

  private static void setYTickUnit(Axis axis, Map attribute){
    if(axis instanceof NumberAxis){
      if(attribute[yTickUnitKeyword] != null)
        axis.setTickUnit((double)attribute[yTickUnitKeyword])
    }
  }

  private static void setXAutoRange(Axis axis, Map attribute){
    if(axis instanceof NumberAxis){
      if(attribute[xAutoRangeKeyword] != null && !attribute[xAutoRangeKeyword])
        axis.setAutoRanging(false)
      else
        axis.setAutoRanging(true)
    }
  }

  private static void setYAutoRange(Axis axis, Map attribute){
    if(axis instanceof NumberAxis){
      if(attribute[yAutoRangeKeyword] != null && !attribute[yAutoRangeKeyword])
        axis.setAutoRanging(false)
      else
        axis.setAutoRanging(true)
    }
  }

  private static void setXAxisTitle(Axis axis, Map attribute){
    if(attribute[xTitleKeyword] != null)
      axis.setLabel(attribute[xTitleKeyword].toString())
  }

  private static void setYAxisTitle(Axis axis, Map attribute){
    if(attribute[yTitleKeyword] != null)
      axis.setLabel(attribute[yTitleKeyword].toString())
  }

  protected static Axis createXAxis(Map attribute){
    Axis axis
    if(attribute[xAxisCategoryKeyword] != null && attribute[xAxisCategoryKeyword])
      axis = new CategoryAxis()
    else
      axis = new NumberAxis()

    setXRange(axis, attribute)
    setXTickUnit(axis, attribute)
    setXAutoRange(axis, attribute)
    setXAxisTitle(axis, attribute)

    return axis
  }

  protected static Axis createYAxis(Map attribute){
    Axis axis
    if(attribute[yAxisCategoryKeyword] != null && attribute[yAxisCategoryKeyword])
      axis = new CategoryAxis()
    else
      axis = new NumberAxis()

    setYRange(axis, attribute)
    setYTickUnit(axis, attribute)
    setYAutoRange(axis, attribute)
    setYAxisTitle(axis, attribute)

    return axis
  }
}