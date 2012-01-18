package main.groovy.gpresentfx.chart

import javafx.scene.chart.BarChart
import javafx.scene.chart.XYChart
import javafx.collections.FXCollections
import com.sun.javafx.collections.ObservableListWrapper
import main.groovy.gpresentfx.GPresentBuilder
import javafx.scene.chart.Axis
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.CategoryAxis

/**
 * @author naokirin
 * BarChartを生成するファクトリクラス
 */
class BarChartFactory extends AbstractXYChartFactory{

  static final String barGapKeyword = 'bargap'
  static final String categoryGapKeyword = 'categorygap'

  /** BarChartのインスタンスを生成する */
  static BarChart newInstance(GPresentBuilder pdb, Map attribute){

    def obj = new BarChart(createXAxis(attribute), createYAxis(attribute), createData(attribute))

    setBarGap(obj, attribute, pdb)
    setCategoryGap(obj, attribute, pdb)
    setTitle(obj, attribute, pdb)
    setWidth(obj, attribute, pdb)
    setHeight(obj, attribute, pdb)
    setRotate(obj, attribute, pdb)
    setPositionX(obj, attribute, pdb)
    setPositionY(obj, attribute, pdb)

    return obj
  }

    /** チャートのデータを生成する */
  private static ObservableListWrapper createData(Map attribute){
    def areaChartSeriesList = []
    if(attribute[main.groovy.gpresentfx.chart.AbstractChartFactory.chartDataKeyword] instanceof Map)
    (attribute[main.groovy.gpresentfx.chart.AbstractChartFactory.chartDataKeyword] as Map).each{series, data ->
      def areaChartSeries = []
      data.each{xValue, yValue ->
        areaChartSeries << new XYChart.Data(xValue, yValue)
      }
      areaChartSeriesList << new XYChart.Series(series.toString(), FXCollections.observableArrayList(areaChartSeries))
    }
    return FXCollections.observableArrayList(areaChartSeriesList)
  }

  private static void setBarGap(BarChart chart, Map attribute, GPresentBuilder pdb){
    if(attribute[barGapKeyword] != null)
      chart.setBarGap((double)attribute[barGapKeyword])
  }

  private static void setCategoryGap(BarChart chart, Map attribute, GPresentBuilder pdb){
    if(attribute[categoryGapKeyword] != null)
      chart.setCategoryGap((double)attribute[categoryGapKeyword])
  }
    protected static Axis createXAxis(Map attribute){
    Axis axis
    if(attribute[xAxisCategoryKeyword] != null && !attribute[xAxisCategoryKeyword])
      axis = new NumberAxis()
    else
      axis = new CategoryAxis()

    setXRange(axis, attribute)
    setXTickUnit(axis, attribute)
    setXAutoRange(axis, attribute)
    setXAxisTitle(axis, attribute)

    return axis
  }
}