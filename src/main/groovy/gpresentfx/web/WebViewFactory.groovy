package main.groovy.gpresentfx.web

import javafx.scene.web.WebView

import main.groovy.gpresentfx.GPresentBuilder
import main.groovy.gpresentfx.AbstractRegionFactory
import javafx.event.EventHandler
import javafx.event.Event
import javafx.scene.input.KeyEvent
import javafx.scene.input.KeyCode
import main.groovy.gpresentfx.GroovyPresentFX
import javafx.concurrent.Worker
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.web.WebEngine
/**
 * @author naokirin
 * WebViewを生成するファクトリクラス
 * TODO: 表示中にたまに例外が発生する問題がある
 */
class WebViewFactory extends AbstractRegionFactory{

  static final String webViewUrlKeyword = 'url'
  static final String webViewFontScaleKeyword = 'fontscale'

  static WebView newInstance(GPresentBuilder pdb, Map attribute){
    def obj = new WebView()

    if(attribute[webViewFontScaleKeyword] != null)
       obj.setFontScale((double)attribute[webViewFontScaleKeyword])

    if(attribute[widthKeyword] != null){
      obj.setMaxWidth((double)attribute[widthKeyword])
      obj.setMinWidth((double)attribute[widthKeyword])
    }

    if(attribute[heightKeyword] != null){
      obj.setMaxHeight((double)attribute[heightKeyword])
      obj.setMinHeight((double)attribute[heightKeyword])
    }

    if(attribute[rotateKeyword] != null)
      obj.setRotate((double)attribute[rotateKeyword])

    if(attribute[positionXKeyword] != null)
       obj.setTranslateX((double) attribute[positionXKeyword])
    if(attribute[positionYKeyword] != null)
       obj.setTranslateY((double) attribute[positionYKeyword])

    if(attribute[webViewUrlKeyword] != null && !attribute[webViewUrlKeyword].toString().trim().isEmpty())
      obj.engine.load(attribute[webViewUrlKeyword].toString())

    return obj
  }
}
