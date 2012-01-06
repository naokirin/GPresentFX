package main.groovy.gpresentfx.chart

import javafx.scene.chart.PieChart
import javafx.scene.chart.PieChart.Data
import javafx.collections.FXCollections
import com.sun.javafx.collections.ObservableListWrapper

class PieChartFactory extends AbstractChartFactory{

  static final String labelVisibleKeyword = 'labelvisible'
  static final String startAngleKeyword = 'startangle'

    /** PieChartの生成 */
  static PieChart newInstance(pdb, Map attribute) {
    def obj = new PieChart(createData(attribute))

    setStartAngle(obj, attribute, pdb)
    setLabelVisible(obj, attribute, pdb)
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
    def pieDataList = []
    (attribute[main.groovy.gpresentfx.chart.AbstractChartFactory.chartDataKeyword] as LinkedHashMap).each {key, value ->
      pieDataList << new Data(key.toString(), (double) value)
    }
    return FXCollections.observableArrayList(pieDataList)
   }

  /** スタートする角度を設定する */
  private static void setStartAngle(PieChart chart, Map attribute, pdb) {
    if (attribute[startAngleKeyword])
      chart.setStartAngle((double)attribute[startAngleKeyword])
  }

  /** ラベルを表示するかを設定する */
  private static void setLabelVisible(PieChart chart, Map attribute, pdb){
    if (attribute[labelVisibleKeyword] != null && !attribute[labelVisibleKeyword])
      chart.setLabelsVisible(false)
  }
}