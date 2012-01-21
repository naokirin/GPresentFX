package test.groovy.gpresentfx.chart

import spock.lang.Specification
import main.groovy.gpresentfx.chart.LineChartFactory
import main.groovy.gpresentfx.GPresentBuilder
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.geometry.Side
import javafx.scene.chart.CategoryAxis

class LineChartFactoryDefaultAttributeTest extends Specification{
  LineChart lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), [:])

  def "data"(){
    expect:
      lineChart.getData().isEmpty()
  }

  def "title"(){
    expect:
      lineChart.getTitle() == null
  }

  def "xtitle"(){
    expect:
      lineChart.getXAxis().getLabel() == null
  }

  def "ytitle"(){
    expect:
      lineChart.getYAxis().getLabel() == null
  }

  def "xcategory"(){
    expect:
      lineChart.getXAxis().class == NumberAxis
  }

  def "ycategory"(){
    expect:
      lineChart.getYAxis().class == NumberAxis
  }

  def "xautorange"(){
    expect:
      lineChart.getXAxis().isAutoRanging()
  }

  def "yautorange"(){
    expect:
      lineChart.getYAxis().isAutoRanging()
  }

  def "rotate"(){
    expect:
      lineChart.getRotate() == 0
  }

  def "posx"(){
    expect:
      lineChart.getTranslateX() == 0
  }

  def "posy"(){
    expect:
      lineChart.getTranslateY() == 0
  }
}

class LineChartFactoryOneSeriesDataAttributeTest extends Specification{
  LineChart lineChart

  def "data of one point"(){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), [data:[a:[0:0]]])
    then:
      lineChart.getData().size() == 1
      lineChart.getData().get(0).class == XYChart.Series
      lineChart.getData().get(0).name == "a"
      lineChart.getData().get(0).data.get(0).class == XYChart.Data
      lineChart.getData().get(0).data.size() == 1
      lineChart.getData().get(0).data.get(0).XValue == 0
      lineChart.getData().get(0).data.get(0).YValue == 0
  }

  def "data of two point"(){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), [data:[a:[0:1, 2:3]]])
    then:
      lineChart.getData().size() == 1
      lineChart.getData().get(0).class == XYChart.Series
      lineChart.getData().get(0).name == "a"
      lineChart.getData().get(0).data.size() == 2
      lineChart.getData().get(0).data.get(0).class == XYChart.Data
      lineChart.getData().get(0).data.get(0).XValue == 0
      lineChart.getData().get(0).data.get(0).YValue == 1
      lineChart.getData().get(0).data.get(1).class == XYChart.Data
      lineChart.getData().get(0).data.get(1).XValue == 2
      lineChart.getData().get(0).data.get(1).YValue == 3
  }
}

class LineChartFactoryTwoSeriesDataAttributeTest extends Specification{
  LineChart lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), [data:["a":[0:0], "b":[1:2]]])

  def "data"(){
    expect:
      lineChart.getData().size() == 2
      lineChart.getData().get(0).class == XYChart.Series
      lineChart.getData().get(0).name == "a"
      lineChart.getData().get(0).data.size() == 1
      lineChart.getData().get(0).data.get(0).class == XYChart.Data
      lineChart.getData().get(0).data.get(0).XValue == 0
      lineChart.getData().get(0).data.get(0).YValue == 0

      lineChart.getData().get(1).class == XYChart.Series
      lineChart.getData().get(1).name == "b"
      lineChart.getData().get(1).data.size() == 1
      lineChart.getData().get(1).data.get(0).class == XYChart.Data
      lineChart.getData().get(1).data.get(0).XValue == 1
      lineChart.getData().get(1).data.get(0).YValue == 2
  }
}

class LineChartFactoryTitleSideAttributeTest extends Specification{
  LineChart lineChart

  def "title"(Map attribute, String title){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      lineChart.getTitle() == title
    where:
      attribute        | title
      [title:""]       | ""
      [title:" "]      |" "
      [title:"azAZ"]  | "azAZ"
      [title:"09"]    | "09"
      [title:"日本語"]| "日本語"
  }

  def "titleside"(Map attribute, Side side){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      lineChart.getTitleSide() == side
    where:
      attribute             | side
      [titleside:"top"]    | Side.TOP
      [titleside:"TOP"]    | Side.TOP
      [titleside:"Top"]    | Side.TOP
      [titleside:"bottom"] | Side.BOTTOM
      [titleside:"right"]  | Side.RIGHT
      [titleside:"left"]   | Side.LEFT
  }
}

class LineChartFactoryAxisTitleAttributeTest extends Specification{
  LineChart lineChart

  def "xtitle"(Map attribute, String title){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      lineChart.getXAxis().getLabel() == title
    where:
      attribute         | title
      [xtitle:""]       | ""
      [xtitle:" "]     | " "
      [xtitle:"azAZ"]  | "azAZ"
      [xtitle:"09"]    | "09"
      [xtitle:"日本語"]| "日本語"
  }

def "ytitle"(Map attribute, String title){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      lineChart.getYAxis().getLabel() == title
    where:
      attribute         | title
      [ytitle:""]       | ""
      [ytitle:" "]     | " "
      [ytitle:"azAZ"]  | "azAZ"
      [ytitle:"09"]    | "09"
      [ytitle:"日本語"]| "日本語"
  }
}

class LineChartFactoryCategoryAttributeTest extends Specification{
  LineChart lineChart

  def "xcategory is true"(){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), [xcategory:true])
    then:
      lineChart.getXAxis().class == CategoryAxis
  }

  def "ycategory is true"(){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), [ycategory:true])
    then:
      lineChart.getYAxis().class == CategoryAxis
  }

  def "xcategory is false"(){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), [xcategory:false])
    then:
      lineChart.getXAxis().class == NumberAxis
  }

  def "ycategory is false"(){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), [ycategory:false])
    then:
      lineChart.getYAxis().class == NumberAxis
  }
}

class LineChartFactoryAutoRangeAttributeTest extends Specification{
  LineChart lineChart

  def "xautorange is false"(){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), [xautorange:false])
    then:
      !lineChart.getXAxis().isAutoRanging()
  }

  def "yautorange is false"(){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), [yautorange:false])
    then:
      !lineChart.getYAxis().isAutoRanging()
  }

  def "xautorange is true"(){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), [xautorange:true])
    then:
      lineChart.getXAxis().isAutoRanging()
  }

  def "yautorange is true"(){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), [yautorange:true])
    then:
      lineChart.getYAxis().isAutoRanging()
  }
}

class LineChartFactoryRangeAttributeTest extends Specification{
  LineChart lineChart

  def "xrange"(Map attribute, double lower, double upper){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.newInstance(), attribute)
    then:
      lineChart.getXAxis().getLowerBound() == lower
      lineChart.getXAxis().getUpperBound() == upper
    where:
      attribute             | lower | upper
      [xrange:[0, 1]]       | 0     | 1
      [xrange:[0.1, 0.0]]   | 0.1   | 0.0
      [xrange:[-0.2, -0.1]] | -0.2  | -0.1
  }
}

class LineChartFactoryUnitAttributeTest extends Specification{
  LineChart lineChart

  def "xunit"(Map attribute, double value){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      lineChart.getXAxis().getTickUnit() == value
    where:
      attribute                     | value
      [xunit:1, xautorange:false]  | 1
      [xunit:0.1, xautorange:false]| 0.1
  }

  def "yunit"(Map attribute, double value){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      lineChart.getYAxis().getTickUnit() == value
    where:
      attribute                     | value
      [yunit:1, yautorange:false]  | 1
      [yunit:0.1, yautorange:false]| 0.1
  }
}

class LineChartFactoryConfigurationAttributeTest extends Specification{
  LineChart lineChart

  def "rotate of plus value"(Map attribute, double value){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      lineChart.getRotate() == value
    where:
      attribute   | value
      [rotate:1]  | 1
      [rotate:0.1]| 0.1
  }

  def "rotate of minus value"(Map attribute, double value){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.newInstance(), attribute)
    then:
      lineChart.getRotate() == value
    where:
      attribute     | value
      [rotate:-1]   | -1
      [rotate: -0.1]| -0.1
  }

  def "rotate over 360"(Map attribute, double value){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.newInstance(), attribute)
    then:
      lineChart.getRotate() == value
    where:
      attribute       | value
      [rotate:361]    | 361
      [rotate:360.1]  | 360.1
      [rotate:-361]   | -361
      [rotate:-360.1] | -360.1
  }

  def "posx"(Map attribute, double value){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      lineChart.getTranslateX() == value
    where:
      attribute   | value
      [posx:0]    | 0
      [posx:0.1]  | 0.1
      [posx:1]    | 1
      [posx:-1]   | -1
      [posx:-0.1] | -0.1
  }

  def "posy"(Map attribute, double value){
    when:
      lineChart = LineChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      lineChart.getTranslateY() == value
    where:
      attribute  | value
      [posy:0]   | 0
      [posy:0.1] | 0.1
      [posy:1]   | 1
      [posy:-1]  | -1
      [posy:-0.1]| -0.1
  }
}