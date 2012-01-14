package main.groovy.gpresentfx.text
import javafx.scene.text.Text

import javafx.scene.text.Font
import javafx.scene.paint.Color
import main.groovy.gpresentfx.GPresentBuilder

import main.groovy.gpresentfx.AbstractNodeFactory

/**
 * @author naokirin
 * Textを生成するファクトリクラス
 */
class TextFactory extends AbstractNodeFactory{

  static final String textTextKeyword = 'text'
  static final String fontFamilyKeyword = 'fontfamily'
  static final String fontSizeKeyword = 'fontsize'
  static final String textColorKeyword = 'textcolor'
  static final String underlineKeyword = 'underline'

  /** テキストの生成 */
  static Text newInstance(GPresentBuilder pdb, Map attribute){
    def obj = createText(attribute)

    setFont(obj, attribute, pdb)
    setUnderline(obj, attribute, pdb)
    setTextColor(obj, attribute, pdb)
    setRotate(obj, attribute, pdb)
    setPositionX(obj, attribute, pdb)
    setPositionY(obj, attribute, pdb)

    return obj
  }

  private static Text createText(Map attribute){
    if(attribute[textTextKeyword] != null)
      return new Text(attribute[textTextKeyword].toString())
    else
      return new Text("")
  }

  private static void setFont(Text obj, Map attribute, GPresentBuilder pdb){
    String family
    if(attribute[fontFamilyKeyword] != null)
      family = attribute[fontFamilyKeyword].toString()
    else
      family = pdb.defaultFontFamily

    obj.setFont(
      Font.font(family, attribute[fontSizeKeyword]!=null?(double)attribute[fontSizeKeyword]:pdb.defaultFontSize))
  }

  private static void setUnderline(Text obj, Map attribute, GPresentBuilder pdb){
    if(attribute[underlineKeyword] != null)
      obj.setUnderline((boolean)attribute[underlineKeyword])
  }

  private static void setTextColor(Text obj, Map attribute, GPresentBuilder pdb){
    def rgbaRegex = /rgba\( *([0-9]+) *, *([0-9]+) *, *([0-9]+) *, *([0-9 \.]+) *\)/
    if(attribute[textColorKeyword] != null){
      if(attribute[textColorKeyword].toString() ==~ rgbaRegex)
        attribute[textColorKeyword].toString().eachMatch(rgbaRegex){
          g0, g1, g2, g3, g4 ->
          obj.setFill(Color.rgb(Integer.parseInt(g1), Integer.parseInt(g2), Integer.parseInt(g3), Double.parseDouble(g4)))
        }
      else
        obj.setFill(Color.web(attribute[textColorKeyword].toString()))
    }
    else{
      if(pdb.defaultTextColor.toString() ==~ rgbaRegex)
        pdb.defaultTextColor.toString().eachMatch(rgbaRegex){
          g0, g1, g2, g3, g4 ->
          obj.setFill(Color.rgb(Integer.parseInt(g1), Integer.parseInt(g2), Integer.parseInt(g3), Double.parseDouble(g4)))
        }
      else{
        obj.setFill(Color.web(pdb.defaultTextColor))
      }
    }
  }
}
