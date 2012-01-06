package main.groovy.gpresentfx

import javafx.geometry.Pos
import javafx.scene.layout.*
import javafx.scene.Node
import javafx.geometry.Insets
import main.groovy.gpresentfx.layout.*
import main.groovy.gpresentfx.chart.*
import main.groovy.gpresentfx.text.*
import main.groovy.gpresentfx.image.*
import main.groovy.gpresentfx.web.WebViewFactory

/**
 * @author naokirin
 * DSLの実装を行っているクラス */
class GPresentBuilder extends BuilderSupport{

  // デフォルトのパラメータの指定
  double defaultWidth = 1024
  double defaultHeight = 700
  Pos defaultAlignment = Pos.CENTER
  Insets defaultPadding = new Insets(0)
  String defaultBackground = "rgba(255,255,255,0)"
  String defaultTextColor = "black"
  String defaultFontFamily = "Arial"
  int defaultFontSize = 30

  // TODO: それぞれのファクトリクラスにキーワードを分離すること
  static final String slidesNameKeyword = 'name'
  static final String windowWidthKeyword = 'width'
  static final String windowHeightKeyword = 'height'
  static final String alignmentKeyword = 'alignment'
  static final String paddingKeyword = 'padding'
  static final String backgroundKeyword = 'background'
  static final String textColorKeyword = 'textcolor'
  static final String fontSizeKeyword = 'fontsize'
  static final String fontFamilyKeyword = 'fontfamily'


  static final String borderTopKeyword = 'top'
  static final String borderBottomKeyword = 'bottom'
  static final String borderCenterKeyword = 'center'
  static final String borderLeftKeyword = 'left'
  static final String borderRightKeyword = 'right'

  // webviewのキーワード
  static final String webViewKeyword = 'webview'
  static final String webViewUrlKeyword = 'url'
  static final String webViewFontScaleKeyword = 'fontscale'


  static LinkedHashMap dslBranchingMap = [
      slides : {pdb, attribute -> return SlidesFactory.newInstance(pdb, attribute)},
      slide : {pdb, attribute -> return SlideFactory.newInstance(pdb, attribute)},
      hbox : {pdb, attribute -> return BoxLayoutFactory.newInstance(pdb, new HBox(), attribute)},
      vbox : {pdb, attribute -> return BoxLayoutFactory.newInstance(pdb, new VBox(), attribute)},
      stack : {pdb, attribute -> return BoxLayoutFactory.newInstance(pdb, new StackPane(), attribute)},
      border : {pdb, attribute -> return BorderPaneFactory.newInstance(pdb, attribute)},
      top : {pdb, attribute -> return BorderLayoutFactory.newInstance(pdb, borderTopKeyword, attribute)},
      left : {pdb, attribute -> return BorderLayoutFactory.newInstance(pdb, borderLeftKeyword, attribute)},
      center : {pdb, attribute -> return BorderLayoutFactory.newInstance(pdb, borderCenterKeyword, attribute)},
      right : {pdb, attribute -> return BorderLayoutFactory.newInstance(pdb, borderRightKeyword, attribute)},
      bottom : {pdb, attribute -> return BorderLayoutFactory.newInstance(pdb, borderBottomKeyword, attribute)},
      text : {pdb, attribute -> return TextFactory.newInstance(pdb, attribute)},
      image : {pdb, attribute -> return ImageViewFactory.newInstance(pdb, attribute)},
      piechart : {pdb, attribute -> return PieChartFactory.newInstance(pdb, attribute)},
      linechart : {pdb, attribute -> return LineChartFactory.newInstance(pdb, attribute)},
      scatterchart : {pdb, attribute -> return ScatterChartFactory.newInstance(pdb, attribute)},
      barchart : {pdb, attribute -> return BarChartFactory.newInstance(pdb, attribute)},
      areachart : {pdb, attribute -> return AreaChartFactory.newInstance(pdb, attribute)},
      bubblechart : {pdb, attribute -> return BubbleChartFactory.newInstance(pdb, attribute)},
      textarea : {pdb, attribute -> return TextAreaFactory.newInstance(pdb, attribute)}]

  // GPresentBuilderのインスタンスを生成
  static GPresentBuilder dsl(){
   return new GPresentBuilder()
 }

  // GPresentBuilderのインスタンスを生成
  static GPresentBuilder dsl(Map attribute){
    def obj = new GPresentBuilder()
    if(attribute[windowWidthKeyword])
      obj.defaultWidth = (float)attribute[windowWidthKeyword]
    if(attribute[windowHeightKeyword])
      obj.defaultHeight = (float)attribute[windowHeightKeyword]
    if(attribute[alignmentKeyword])
      obj.defaultAlignment = TreatingAlignment.stringToAlignment(attribute[alignmentKeyword].toString())
    if(attribute[backgroundKeyword])
      obj.defaultBackground = attribute[backgroundKeyword].toString()
    if(attribute[textColorKeyword])
      obj.defaultTextColor = attribute[textColorKeyword].toString()
    if(attribute[fontSizeKeyword])
      obj.defaultFontSize = (int)attribute[fontSizeKeyword]
    if(attribute[fontFamilyKeyword])
      obj.defaultFontFamily = attribute[fontFamilyKeyword]

    return obj
  }

  /** 親子関係の処理 */
  protected void setParent(Object parent, Object child){
    if(parent != null && child != null){
      if(parent instanceof Slides)
        parent.present << child
      else if(parent instanceof BorderPane && child.metaClass.hasProperty(child, 'borderPos')){
        if(child.getBorderPos() == borderTopKeyword)
          parent.setTop(child as Node)
        else if(child.getBorderPos() == borderLeftKeyword)
          parent.setLeft(child as Node)
        else if(child.getBorderPos() == borderCenterKeyword)
          parent.setCenter(child as Node)
        else if(child.getBorderPos() == borderRightKeyword)
          parent.setRight(child as Node)
        else if(child.getBorderPos() == borderBottomKeyword)
          parent.setBottom(child as Node)
      }
      else
        parent.children.add(child)
    }
  }

  /** 属性なしのノードの処理 */
  protected Object createNode(Object name){
    return createNode(name, [:])
  }

  /** 属性ありのノードの処理 */
  protected Object createNode(Object name, Map attribute){
    if(dslBranchingMap[name.toString()] != null)
      return dslBranchingMap[name.toString()].call([this, attribute])
    else
      return null
  }

  /** 属性なしで値を渡された時のノードの処理 */
  protected Object createNode(Object name, Object value){
    return createNode(name, [:], value)
  }

  /** 属性ありで値を渡された時のノードの処理 */
  protected Object createNode(Object name, Map attribute, Object value){
    def obj = createNode(name, attribute)
    if(value instanceof List)
      value.each{
        setParent(obj, it)
      }
    else
      setParent(obj, value)
    
    return obj
  }
}