package main.groovy.gpresentfx.chart

import javafx.scene.chart.LineChart
import javafx.scene.chart.XYChart
import javafx.collections.FXCollections
import com.sun.javafx.collections.ObservableListWrapper
import main.groovy.gpresentfx.GPresentBuilder

/**
 * @author naokirin
 * LineChartを生成するファクトリクラス
 */
class LineChartFactory extends AbstractXYChartFactory{

  /** LineChartのインスタンスを生成する */
  static LineChart newInstance(GPresentBuilder pdb, Map attribute){

    def obj = new LineChart(createXAxis(attribute), createYAxis(attribute), createData(attribute))

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
}
