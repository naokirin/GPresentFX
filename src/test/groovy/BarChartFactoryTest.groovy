package test.groovy

import spock.lang.Specification
import main.groovy.gpresentfx.chart.BarChartFactory
import main.groovy.gpresentfx.GPresentBuilder
import javafx.scene.chart.BarChart
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.geometry.Side
import javafx.scene.chart.CategoryAxis
import javafx.geometry.Pos

class BarChartFactoryDefaultAttributeTest extends Specification{
  BarChart barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), [xcategory:true])

  def "data"(){
    expect:
      barChart.getData().isEmpty()
  }

  def "title"(){
    expect:
      barChart.getTitle() == null
  }

  def "xtitle"(){
    expect:
      barChart.getXAxis().getLabel() == null
  }

  def "ytitle"(){
    expect:
      barChart.getYAxis().getLabel() == null
  }

  def "xcategory"(){
    expect:
      barChart.getXAxis().class == CategoryAxis
  }

  def "ycategory"(){
    expect:
      barChart.getYAxis().class == NumberAxis
  }

  def "xautorange"(){
    expect:
      barChart.getXAxis().isAutoRanging()
  }

  def "yautorange"(){
    expect:
      barChart.getYAxis().isAutoRanging()
  }

  def "rotate"(){
    expect:
      barChart.getRotate() == 0
  }

  def "posx"(){
    expect:
      barChart.getTranslateX() == 0
  }

  def "posy"(){
    expect:
      barChart.getTranslateY() == 0
  }
}

class BarChartFactoryOneSeriesDataAttributeTest extends Specification{
  BarChart barChart

  def "data of one point"(){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), [data:[a:[a:0]]])
    then:
      barChart.getData().size() == 1
      barChart.getData().get(0).class == XYChart.Series
      barChart.getData().get(0).name == "a"
      barChart.getData().get(0).data.get(0).class == XYChart.Data
      barChart.getData().get(0).data.size() == 1
      barChart.getData().get(0).data.get(0).XValue == "a"
      barChart.getData().get(0).data.get(0).YValue == 0
  }

  def "data of two point"(){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), [data:[a:[a:1, b:3]]])
    then:
      barChart.getData().size() == 1
      barChart.getData().get(0).class == XYChart.Series
      barChart.getData().get(0).name == "a"
      barChart.getData().get(0).data.size() == 2
      barChart.getData().get(0).data.get(0).class == XYChart.Data
      barChart.getData().get(0).data.get(0).XValue == "a"
      barChart.getData().get(0).data.get(0).YValue == 1
      barChart.getData().get(0).data.get(1).class == XYChart.Data
      barChart.getData().get(0).data.get(1).XValue == "b"
      barChart.getData().get(0).data.get(1).YValue == 3
  }
}

class BarChartFactoryTwoSeriesDataAttributeTest extends Specification{
  BarChart barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), [data:["a":[a:0], "b":[b:2]]])

  def "data"(){
    expect:
      barChart.getData().size() == 2
      barChart.getData().get(0).class == XYChart.Series
      barChart.getData().get(0).name == "a"
      barChart.getData().get(0).data.size() == 1
      barChart.getData().get(0).data.get(0).class == XYChart.Data
      barChart.getData().get(0).data.get(0).XValue == "a"
      barChart.getData().get(0).data.get(0).YValue == 0

      barChart.getData().get(1).class == XYChart.Series
      barChart.getData().get(1).name == "b"
      barChart.getData().get(1).data.size() == 1
      barChart.getData().get(1).data.get(0).class == XYChart.Data
      barChart.getData().get(1).data.get(0).XValue == "b"
      barChart.getData().get(1).data.get(0).YValue == 2
  }
}

class BarChartFactoryTitleSideAttributeTest extends Specification{
  BarChart barChart

  def "title"(Map attribute, String title){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      barChart.getTitle() == title
    where:
      attribute << [[title:""], [title:" "], [title:"azAZ"], [title:"09"], [title:"日本語"]]
      title     << [""        , " "        , "azAZ"        , "09"        , "日本語"]
  }

  def "titleside"(Map attribute, Side side){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      barChart.getTitleSide() == side
    where:
      attribute << [[titleside:"top"], [titleside:"TOP"], [titleside:"Top"], [titleside:"bottom"], [titleside:"right"], [titleside:"left"]]
      side      << [Side.TOP         , Side.TOP          , Side.TOP         , Side.BOTTOM          , Side.RIGHT         , Side.LEFT]
  }
}

class BarChartFactoryAxisTitleAttributeTest extends Specification{
  BarChart barChart

  def "xtitle"(Map attribute, String title){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      barChart.getXAxis().getLabel() == title
    where:
      attribute << [[xtitle:""], [xtitle:" "], [xtitle:"azAZ"], [xtitle:"09"], [xtitle:"日本語"]]
      title     << [""        , " "        , "azAZ"        , "09"        , "日本語"]
  }

def "ytitle"(Map attribute, String title){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      barChart.getYAxis().getLabel() == title
    where:
      attribute << [[ytitle:""], [ytitle:" "], [ytitle:"azAZ"], [ytitle:"09"], [ytitle:"日本語"]]
      title     << [""        , " "        , "azAZ"        , "09"        , "日本語"]
  }
}

class BarChartFactoryCategoryAttributeTest extends Specification{
  BarChart barChart

  def "xcategory is true"(){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), [xcategory:true])
    then:
      barChart.getXAxis().class == CategoryAxis
  }

  def "ycategory is true"(){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), [ycategory:true, xcategory:false])
    then:
      barChart.getYAxis().class == CategoryAxis
  }

  def "xcategory is false"(){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), [xcategory:false, ycategory:true])
    then:
      barChart.getXAxis().class == NumberAxis
  }

  def "ycategory is false"(){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), [ycategory:false])
    then:
      barChart.getYAxis().class == NumberAxis
  }
}

class BarChartFactoryAutoRangeAttributeTest extends Specification{
  BarChart barChart

  def "xautorange is false"(){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), [xautorange:false, xcategory:false, ycategory:true])
    then:
      !barChart.getXAxis().isAutoRanging()
  }

  def "yautorange is false"(){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), [yautorange:false])
    then:
      !barChart.getYAxis().isAutoRanging()
  }

  def "xautorange is true"(){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), [xautorange:true])
    then:
      barChart.getXAxis().isAutoRanging()
  }

  def "yautorange is true"(){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), [yautorange:true])
    then:
      barChart.getYAxis().isAutoRanging()
  }
}

class BarChartFactoryRangeAttributeTest extends Specification{
  BarChart barChart

  def "xrange"(Map attribute, double lower, double upper){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.newInstance(), attribute)
    then:
      barChart.getXAxis().getLowerBound() == lower
      barChart.getXAxis().getUpperBound() == upper
    where:
      attribute << [[xrange:[0, 1], xcategory:false, ycategory:true], [xrange:[0.0, 0.1], xcategory:false, ycategory:true], [xrange:[-0.2, -0.1], xcategory:false, ycategory:true]]
      lower     << [0              , 0.0                , -0.2]
      upper     << [1              , 0.1                , -0.1]
  }
}

class BarChartFactoryUnitAttributeTest extends Specification{
  BarChart barChart

  def "xunit"(Map attribute, double value){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      barChart.getXAxis().getTickUnit() == value
    where:
      attribute << [[xunit:1, xautorange:false, xcategory:false, ycategory:true], [xunit:0.1, xautorange:false, xcategory:false, ycategory:true]]
      value     << [1        , 0.1]
  }

  def "yunit"(Map attribute, double value){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      barChart.getYAxis().getTickUnit() == value
    where:
      attribute << [[yunit:1, yautorange:false], [yunit:0.1, yautorange:false]]
      value     << [1        , 0.1]
  }
}

class BarChartFactoryGapAttributeTest extends Specification{
  BarChart barChart

  def "categorygap"(Map attribute, double value){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.newInstance(), attribute)
    then:
      barChart.getCategoryGap() == value
    where:
      attribute << [[categorygap:1], [categorygap:0.1], [categorygap:10.1f]]
      value     << [1              , 0.1              , 10.1f]
  }

  def "bargap"(Map attribute, double value){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.newInstance(), attribute)
    then:
      barChart.getBarGap() == value
    where:
      attribute << [[bargap:1], [bargap:0.1], [bargap:10.1f]]
      value     << [1         , 0.1         , 10.1f]
  }
}

class BarChartFactoryConfigurationAttributeTest extends Specification{
  BarChart barChart

  def "rotate of plus value"(Map attribute, double value){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      barChart.getRotate() == value
    where:
      attribute << [[rotate:1], [rotate:0.1], [rotate:10.0f]]
      value     << [1         , 0.1         , 10.0]
  }

  def "rotate of minus value"(Map attribute, double value){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.newInstance(), attribute)
    then:
      barChart.getRotate() == value
    where:
      attribute << [[rotate:-1], [rotate: -0.1], [rotate:-10.0f]]
      value     << [-1         , -0.1          , -10.0f]
  }

  def "rotate over 360"(Map attribute, double value){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.newInstance(), attribute)
    then:
      barChart.getRotate() == value
    where:
      attribute << [[rotate:361], [rotate:360.1], [rotate:360.0f], [rotate:-361], [rotate:-360.1]]
      value     << [361         , 360.1         , 360.0f         , -361         , -360.1]
  }

  def "posx"(Map attribute, double value){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      barChart.getTranslateX() == value
    where:
      attribute << [[posx:0], [posx:0.1], [posx:1], [posx:0.1f], [posx:-1], [posx:-0.1]]
      value     << [0       , 0.1       , 1       , 0.1f       , -1       , -0.1]
  }

  def "posy"(Map attribute, double value){
    when:
      barChart = BarChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      barChart.getTranslateY() == value
    where:
      attribute << [[posy:0], [posy:0.1], [posy:1], [posy:0.1f], [posy:-1], [posy:-0.1]]
      value     << [0       , 0.1       , 1       , 0.1f       , -1       , -0.1]
  }
}