package main.groovy.gpresentfx.text

import javafx.scene.control.TextArea
import main.groovy.gpresentfx.GPresentBuilder
import main.groovy.gpresentfx.AbstractNodeFactory
import javafx.event.EventHandler
import javafx.event.Event
import javafx.scene.input.KeyEvent
import javafx.scene.input.KeyCode
import main.groovy.gpresentfx.GroovyPresentFX

class TextAreaFactory extends AbstractNodeFactory{

  static final String textAreaTextKeyword = 'text'
  static final String widthKeyword = 'width'
  static final String heightKeyword = 'height'
  static final String editableKeyword = 'editable'
  static final String scaleKeyword = 'scale'
  static final String columnKeyword = 'column'
  static final String rowKeyword = 'row'

  static TextArea newInstance(GPresentBuilder pdb, Map attribute){
    def obj = createTextArea(attribute)

    setWidth(obj, attribute, pdb)
    setHeight(obj, attribute, pdb)
    setPositionX(obj, attribute, pdb)
    setPositionY(obj, attribute, pdb)
    setRotate(obj, attribute, pdb)
    setEditable(obj, attribute, pdb)
    setColumn(obj, attribute, pdb)
    setRow(obj, attribute, pdb)

    if(obj.editable){
      obj.setOnKeyPressed(new EventHandler(){
        void handle(Event event) {
          event = event as KeyEvent
          if(event.getCode() == KeyCode.PAGE_DOWN){
            GroovyPresentFX.prev()
          }
          else if(event.getCode() == KeyCode.PAGE_UP){
            GroovyPresentFX.next()
          }
        }
      })
    }
    else{
      obj.setOnKeyPressed(new EventHandler(){
        void handle(Event event) {
          event = event as KeyEvent
          if(event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.PAGE_DOWN){
            GroovyPresentFX.prev()
          }
          else if(event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.PAGE_UP){
            GroovyPresentFX.next()
          }
        }
      })
    }
    return obj
  }

  private static TextArea createTextArea(Map attribute){
    return new TextArea(attribute[textAreaTextKeyword].toString())
  }

  private static void setWidth(TextArea area, Map attribute, GPresentBuilder pdb){
    if(attribute[widthKeyword] != null){
      area.setMaxWidth((double)attribute[widthKeyword])
      area.setMinWidth((double)attribute[widthKeyword])
    }
  }

  private static void setHeight(TextArea area, Map attribute, GPresentBuilder pdb){
    if(attribute[heightKeyword] != null){
      area.setMaxHeight((double)attribute[heightKeyword])
      area.setMinHeight((double)attribute[heightKeyword])
    }
  }

  private static void setEditable(TextArea area, Map attribute, GPresentBuilder pdb){
    if(attribute[editableKeyword] != null)
      area.setEditable((boolean)attribute[editableKeyword])
  }

  private static void setColumn(TextArea area, Map attribute, GPresentBuilder pdb){
    if(attribute[columnKeyword] != null)
      area.setPrefColumnCount((int)attribute[columnKeyword])
  }

  private static void setRow(TextArea area, Map attribute, GPresentBuilder pdb){
    if(attribute[rowKeyword] != null)
      area.setPrefRowCount((int)attribute[rowKeyword])
  }
}
