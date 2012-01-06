package main.groovy.gpresentfx.chart

import javafx.scene.chart.ScatterChart
import javafx.scene.chart.XYChart
import main.groovy.gpresentfx.GPresentBuilder
import javafx.collections.FXCollections
import com.sun.javafx.collections.ObservableListWrapper

/**
 * @author naokirin
 * ScatterChartを生成するファクトリクラス
 */
class ScatterChartFactory extends AbstractXYChartFactory{
   /** ScatterChartの生成 */
  static ScatterChart newInstance(GPresentBuilder pdb, Map attribute){
    def obj = new ScatterChart(createXAxis(attribute), createYAxis(attribute), createData(attribute))

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
