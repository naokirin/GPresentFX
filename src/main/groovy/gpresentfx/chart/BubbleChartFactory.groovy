package main.groovy.gpresentfx.chart

import javafx.scene.chart.BubbleChart
import main.groovy.gpresentfx.GPresentBuilder

import javafx.scene.chart.XYChart
import javafx.collections.FXCollections

import com.sun.javafx.collections.ObservableListWrapper

/**
 * @author naokirin
 * BubbleChartを生成するファクトリクラス
 */
class BubbleChartFactory extends AbstractXYChartFactory{

  /** BubbleChartのインスタンスを生成する */
  static BubbleChart newInstance(GPresentBuilder pdb, Map attribute){

    def obj = new BubbleChart(createXAxis(attribute), createYAxis(attribute), createData(attribute))

    setTitle(obj, attribute, pdb)
    setWidth(obj, attribute, pdb)
    setHeight(obj, attribute, pdb)
    setRotate(obj, attribute, pdb)
    setPositionX(obj, attribute, pdb)
    setPositionY(obj, attribute, pdb)

    return obj
  }

  /** チャートのデータを生成する */
  private static ObservableListWrapper createData(attribute){
    def bubbleChartSeriesList = []
    if(attribute[main.groovy.gpresentfx.chart.AbstractChartFactory.chartDataKeyword] instanceof Map){
      (attribute[main.groovy.gpresentfx.chart.AbstractChartFactory.chartDataKeyword] as Map).each{series, data ->
        def bubbleChartSeries = []
        if(data instanceof List){
          data.each{
            if(it instanceof List && it.size() == 3)
            bubbleChartSeries << new XYChart.Data(it[0], it[1], it[2])
          }
          bubbleChartSeriesList << new XYChart.Series(series.toString(), FXCollections.observableArrayList(bubbleChartSeries))
        }
      }
    }
    return FXCollections.observableArrayList(bubbleChartSeriesList)
  }
}