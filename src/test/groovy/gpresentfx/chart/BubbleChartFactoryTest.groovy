package test.groovy.gpresentfx.chart

import spock.lang.Specification
import main.groovy.gpresentfx.chart.BubbleChartFactory
import main.groovy.gpresentfx.GPresentBuilder
import javafx.scene.chart.BubbleChart
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.geometry.Side
import javafx.scene.chart.CategoryAxis

class BubbleChartFactoryDefaultAttributeTest extends Specification{
  BubbleChart bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), [:])

  def "data"(){
    expect:
      bubbleChart.getData().isEmpty()
  }

  def "title"(){
    expect:
      bubbleChart.getTitle() == null
  }

  def "xtitle"(){
    expect:
      bubbleChart.getXAxis().getLabel() == null
  }

  def "ytitle"(){
    expect:
      bubbleChart.getYAxis().getLabel() == null
  }

  def "xcategory"(){
    expect:
      bubbleChart.getXAxis().class == NumberAxis
  }

  def "ycategory"(){
    expect:
      bubbleChart.getYAxis().class == NumberAxis
  }

  def "xautorange"(){
    expect:
      bubbleChart.getXAxis().isAutoRanging()
  }

  def "yautorange"(){
    expect:
      bubbleChart.getYAxis().isAutoRanging()
  }

  def "rotate"(){
    expect:
      bubbleChart.getRotate() == 0
  }

  def "posx"(){
    expect:
      bubbleChart.getTranslateX() == 0
  }

  def "posy"(){
    expect:
      bubbleChart.getTranslateY() == 0
  }
}

class BubbleChartFactoryOneSeriesDataAttributeTest extends Specification{
  BubbleChart bubbleChart

  def "data of one point"(){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), [data:[a:[[0, 0, 0]]]])
    then:
      bubbleChart.getData().size() == 1
      bubbleChart.getData().get(0).class == XYChart.Series
      bubbleChart.getData().get(0).name == "a"
      bubbleChart.getData().get(0).data.get(0).class == XYChart.Data
      bubbleChart.getData().get(0).data.size() == 1
      bubbleChart.getData().get(0).data.get(0).XValue == 0
      bubbleChart.getData().get(0).data.get(0).YValue == 0
      bubbleChart.getData().get(0).data.get(0).extraValue == 0
  }

  def "data of two point"(){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), [data:[a:[[0, 1, 2] ,[3, 4, 5]]]])
    then:
      bubbleChart.getData().size() == 1
      bubbleChart.getData().get(0).class == XYChart.Series
      bubbleChart.getData().get(0).name == "a"
      bubbleChart.getData().get(0).data.size() == 2
      bubbleChart.getData().get(0).data.get(0).class == XYChart.Data
      bubbleChart.getData().get(0).data.get(0).XValue == 0
      bubbleChart.getData().get(0).data.get(0).YValue == 1
      bubbleChart.getData().get(0).data.get(0).extraValue == 2
      bubbleChart.getData().get(0).data.get(1).class == XYChart.Data
      bubbleChart.getData().get(0).data.get(1).XValue == 3
      bubbleChart.getData().get(0).data.get(1).YValue == 4
      bubbleChart.getData().get(0).data.get(1).extraValue == 5
  }

  def "data of empty"(){
    when:
       BubbleChart bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), [data:[a:[]]])
    then:
      bubbleChart.getData().size() == 1
      bubbleChart.getData().get(0).class == XYChart.Series
      bubbleChart.getData().get(0).name == "a"
      bubbleChart.getData().get(0).data.size() == 0
  }
}

class BubbleChartFactoryTwoSeriesDataAttributeTest extends Specification{
  BubbleChart bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), [data:["a":[[0, 0, 0]], "b":[[1, 2, 3]]]])

  def "data"(){
    expect:
      bubbleChart.getData().size() == 2
      bubbleChart.getData().get(0).class == XYChart.Series
      bubbleChart.getData().get(0).name == "a"
      bubbleChart.getData().get(0).data.size() == 1
      bubbleChart.getData().get(0).data.get(0).class == XYChart.Data
      bubbleChart.getData().get(0).data.get(0).XValue == 0
      bubbleChart.getData().get(0).data.get(0).YValue == 0
      bubbleChart.getData().get(0).data.get(0).extraValue == 0

      bubbleChart.getData().get(1).class == XYChart.Series
      bubbleChart.getData().get(1).name == "b"
      bubbleChart.getData().get(1).data.size() == 1
      bubbleChart.getData().get(1).data.get(0).class == XYChart.Data
      bubbleChart.getData().get(1).data.get(0).XValue == 1
      bubbleChart.getData().get(1).data.get(0).YValue == 2
      bubbleChart.getData().get(1).data.get(0).extraValue == 3
  }
}

class BubbleChartFactoryTitleSideAttributeTest extends Specification{
  BubbleChart bubbleChart

  def "title"(Map attribute, String title){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      bubbleChart.getTitle() == title
    where:
      attribute       | title
      [title:""]      | ""
      [title:" "]     | " "
      [title:"azAZ"] | "azAZ"
      [title:"09"]    | "09"
      [title:"日本語"]| "日本語"
  }

  def "titleside"(Map attribute, Side side){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      bubbleChart.getTitleSide() == side
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

class BubbleChartFactoryAxisTitleAttributeTest extends Specification{
  BubbleChart bubbleChart

  def "xtitle"(Map attribute, String title){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      bubbleChart.getXAxis().getLabel() == title
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
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      bubbleChart.getYAxis().getLabel() == title
    where:
      attribute         | title
      [ytitle:""]       | ""
      [ytitle:" "]      | " "
      [ytitle:"azAZ"]   | "azAZ"
      [ytitle:"09"]     | "09"
      [ytitle:"日本語"] | "日本語"
  }
}

class BubbleChartFactoryCategoryAttributeTest extends Specification{
  BubbleChart bubbleChart

  def "xcategory is true"(){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), [xcategory:true])
    then:
      bubbleChart.getXAxis().class == CategoryAxis
  }

  def "ycategory is true"(){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), [ycategory:true])
    then:
      bubbleChart.getYAxis().class == CategoryAxis
  }

  def "xcategory is false"(){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), [xcategory:false])
    then:
      bubbleChart.getXAxis().class == NumberAxis
  }

  def "ycategory is false"(){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), [ycategory:false])
    then:
      bubbleChart.getYAxis().class == NumberAxis
  }
}

class BubbleChartFactoryAutoRangeAttributeTest extends Specification{
  BubbleChart bubbleChart

  def "xautorange is false"(){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), [xautorange:false])
    then:
      !bubbleChart.getXAxis().isAutoRanging()
  }

  def "yautorange is false"(){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), [yautorange:false])
    then:
      !bubbleChart.getYAxis().isAutoRanging()
  }

  def "xautorange is true"(){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), [xautorange:true])
    then:
      bubbleChart.getXAxis().isAutoRanging()
  }

  def "yautorange is true"(){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), [yautorange:true])
    then:
      bubbleChart.getYAxis().isAutoRanging()
  }
}

class BubbleChartFactoryRangeAttributeTest extends Specification{
  BubbleChart bubbleChart

  def "xrange"(Map attribute, double lower, double upper){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.newInstance(), attribute)
    then:
      bubbleChart.getXAxis().getLowerBound() == lower
      bubbleChart.getXAxis().getUpperBound() == upper
    where:
      attribute             | lower | upper
      [xrange:[0, 1]]       | 0     | 1
      [xrange:[0.1, 0.0]]   | 0.1   | 0.0
      [xrange:[-0.2, -0.1]] | -0.2  |-0.1
  }
}

class BubbleChartFactoryUnitAttributeTest extends Specification{
  BubbleChart bubbleChart

  def "xunit"(Map attribute, double value){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      bubbleChart.getXAxis().getTickUnit() == value
    where:
      attribute                      | value
      [xunit:1, xautorange:false]   | 1
      [xunit:0.1, xautorange:false] | 0.1
  }

  def "yunit"(Map attribute, double value){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      bubbleChart.getYAxis().getTickUnit() == value
    where:
      attribute                      | value
      [yunit:1, yautorange:false]   | 1
      [yunit:0.1, yautorange:false] | 0.1
  }
}

class BubbleChartFactoryConfigurationAttributeTest extends Specification{
  BubbleChart bubbleChart

  def "rotate of plus value"(Map attribute, double value){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      bubbleChart.getRotate() == value
    where:
      attribute      | value
      [rotate:0]     | 0
      [rotate:1]     | 1
      [rotate:0.1]   | 0.1
  }

  def "rotate of minus value"(Map attribute, double value){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.newInstance(), attribute)
    then:
      bubbleChart.getRotate() == value
    where:
      attribute      | value
      [rotate:-1]    | -1
      [rotate: -0.1] | -0.1
  }

  def "rotate over 360"(Map attribute, double value){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.newInstance(), attribute)
    then:
      bubbleChart.getRotate() == value
    where:
      attribute      | value
      [rotate:361]   | 361
      [rotate:360.1] | 360.1
      [rotate:-361]  | -361
      [rotate:-360.1]| -360.1
  }

  def "posx"(Map attribute, double value){
    when:
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      bubbleChart.getTranslateX() == value
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
      bubbleChart = BubbleChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      bubbleChart.getTranslateY() == value
    where:
      attribute  | value
      [posy:0]   | 0
      [posy:0.1] | 0.1
      [posy:1]   | 1
      [posy:-1]  | -1
      [posy:-0.1]| -0.1
  }
}