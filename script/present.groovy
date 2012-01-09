import main.groovy.gpresentfx.GPresentBuilder

// スライドオブジェクトを生成するクロージャ
// 同じような表示を繰り返し使うときに便利
def cacheSlide = {
  GPresentBuilder.dsl(width:530, height: 500, alignment:"center", background:"steelblue",
    fontfamily:"IPA Pゴシック", fontsize: 50).stack{
    text(text:"○", fontsize:230, textcolor: "red")
    text(text:"Java", fontsize:70, textcolor:"red")
  }
}

def sunSlide = {
  GPresentBuilder.dsl(fontfamily:"IPA Pゴシック", fontsize:50).stack(width:530, height: 500){

    border(rotate:10){
      left  {text(text:"＝＝", fontsize:100, textcolor:"red")}
      center{text(text:"○", fontsize:230, textcolor:"red")}
      right {text(text:"＝＝", fontsize:100, textcolor:"red")}
    }
    border(rotate:55){
      left  {text(text:"＝＝", fontsize:100, textcolor:"red")}
      right {text(text:"＝＝", fontsize:100, textcolor:"red")}
    }
    border(rotate:100){
      left  {text(text:"＝＝", fontsize:100, textcolor:"red")}
      right {text(text:"＝＝", fontsize:100, textcolor:"red")}
    }
    border(rotate:145){
      left  {text(text:"＝＝", fontsize:100, textcolor:"red")}
      right {text(text:"＝＝", fontsize:100, textcolor:"red")}
    }
  }
}

def lastText ={
  GPresentBuilder.dsl(fontfamily:"IPA Pゴシック", fontsize:50).vbox(posx:50, posy:100){
    text(text:"こんなこともできます", fontsize:40, textcolor:"white")
  }
}

def oracleSlide = {
  GPresentBuilder.dsl(fontfamily:"IPA Pゴシック", fontsize:50).stack(rotate:-30){
    border{
      center{text(text:"○", fontsize:230, textcolor:"red")}
    }
    text(text:"racle", textcolor:"red", posx:170)
  }
}

def cacheText = {GPresentBuilder.dsl(width:1024, height: 700, alignment:"center", background:"steelblue",
  fontfamily:"IPA Pゴシック", fontsize: 70).vbox(width:1500, height:700, background:"rgba(255,255,255,0.8)"){
      hbox(rotate:10){text(text:"今回は"); text(text:"Groovy", textcolor:"#0000AA", fontsize:77); text(text:"です。")}
    }
}

def now = {"現在時刻は ${new Date().timeString}"}
def testText = {
  def txt = GPresentBuilder.dsl(fontfamily:"IPA Pゴシック",fontsize:50).text(text:now(), textcolor:"floralwhite")
  println txt
  def changeText = {
    Integer.MAX_VALUE.times{
    txt.setText(now())
    sleep(1000)
  }} as Runnable
  new Thread(changeText).start()
  return txt
}

def timebox = {GPresentBuilder.dsl().vbox([{GPresentBuilder.dsl(fontfamily:"IPA Pゴシック").vbox(padding: 50){
  text(text:"""DSLはGroovyスクリプトなので
外部DSLスクリプトだけで下のようなことも可能です""", fontsize:40)}}(),
  testText(),
  GPresentBuilder.dsl().vbox(padding:50){text(text:"さすがにJavaFXの知識が必要になりますが…     ", fontsize:20)}])
}

// DSLスクリプトはslidesが最終的に処理されるようになっている必要がある
GPresentBuilder.dsl(alignment:"center", background:"steelblue", fontfamily:"IPA Pゴシック", fontsize: 50, padding:[20, 0], width:1024, height: 700)
  .slides(name:"Groovy Presentation FX", pagecounter:true){

  slide{
    text(text:"DSLで作れるプレゼン", fontsize: 90, textcolor:"floralwhite")
    text(text:"Groovy Presentation FX", textcolor:"rgba(255, 0, 0, 1)")
  }

  slide{
    text(text:"Groovy Presentation FXとは", textcolor:"red", underline: true)
  }

  slide{
    text(text:"DSLで書けるプレゼンテーションツール", textcolor: "floralwhite")
    text(text:"表示はJavaFX", fontsize: 40)
  }

  slide(overlap:true, [cacheSlide(), sunSlide(), lastText()]) // 子を持つことが可能なオブジェクトにオブジェクトを追加することも可能

  slide(overlap:true, [cacheSlide(), lastText()]) // 複数のオブジェクトを入れることも可能、ただしリストの順序に沿って格納される

  slide(overlap:true, [cacheSlide(), oracleSlide(), lastText()])

  slide(overlap:true, [cacheSlide(), oracleSlide(), lastText(), cacheText()])

  slide{
    vbox{
      text(text:"画像の表示もちゃんとできます", fontsize: 60)
      image(url:"icon02.jpg", height:300, preservedratio: true)
    }
  }

  slide{
    hbox{
      vbox{text(text: "Naoki_Rinです！  ", fontfamily:"arial", fontsize:40)
        text(text: "よろしく！    ", fontfamily: "serif", fontsize:40, posy:10)}
      image(url:"icon02.jpg", width:150)
    }
    hbox{
      text(text: "上のように横に並べて表示もできます")
    }
  }

  slide{
    text(fontsize: 50, text: "チャート図なども表示できます")
    hbox{
      vbox(background:"floralwhite", width:400, height:400){
        piechart(data:["無回答 ": 3, "使わない  ": 4, "使う ": 98], startangle:40, labelvisible:false, title:"インターネットを使う？(仮)       ")
      }
      scatterchart(title:"PC vs. Internet(1日あたり)", xtitle:"PC", ytitle:"Internet", yrange:[-1.0, 7.0], yunit:1.0, data:["3時間以下  ":[0:0, 1:0.5, 2:1.5, 3:2.6], "8時間以下  ":[4:3.0, 5:3.5, 6:4.8, 7:5.0, 8:6.0], "8時間以上":[9:6.0, 10:6.3]])
    }
  }

  slide{
    text(fontsize: 50, text: "折れ線グラフもOK")
    hbox{
      linechart(data:["1組":[4:1.6, 5:2.1, 6:3.5, 7:3.3], "2組":[4:0.5, 5:3.4, 6:2.8, 7:3.0]], xrange: [4.0, 7.0], xunit:1, xtitle:"月", ytitle:"通信簿平均", title:"通信簿平均(月)")
      linechart(data:["f":["A":10, "B":4, "C":5], "g":["A":3, "B":2, "C":4]], xcategory:true)
    }
  }

  slide{
    text(text:"棒グラフも", fontsize:50)
    hbox{
      barchart(xcategory:true,
        data:["Abstract":["A":10, "B": 13], "Interface":["A":3, "B":6], "Class":["A":31, "B":38]],
        categorygap:10, bargap:0)
      barchart(data:["":[1:'1', 2:'2', 3:'3', 4:'4']], ycategory:true, xcategory:false)
    }
  }

  slide{
    text(text:"こんなグラフも", fontsize: 50)
    hbox{
      areachart(data:["A":[0:0, 1:3, 2:2, 3:7, 5:9], "B":[0:0, 1:1, 2:4, 3:6, 4:1, 5:3]], xrange:[0, 5], xunit:1)
      bubblechart(data:["A":[[0, 0, 1], [-1, 1, 0.3]], "B":[[1, 2, 0.8], [2, 1, 0.5]]])
    }
  }



  slide(alignment:"top"){
    border{
      top{
        vbox(background:"floralwhite"){text(text:"ショートカット機能", fontsize:60)}
      }
      center(padding:[140,0]){
        text(text:"HOMEキーで1枚目のスライド、")
        text(posy:100, text:"ENDキーで最後のスライドに移動します")
      }
    }
  }

  slide(alignment:"top"){
    border{
      top{
        vbox(background:"floralwhite"){text(text:"さらに", fontsize:60)}
      }
      center(padding:[140,0]){
        text(text:"""F5を押すだけで外部DSLスクリプトを
リロードする機能もあります""")
        text(posy:100, text:"↑これ、結構便利", fontsize: 30)
      }
    }
  }

  slide(alignment:"top"){
    border{
      top{
        vbox(background:"floralwhite"){text(text:"問題点", fontsize: 60)}
      }
      center{
        stack(padding:[100, 0, 50, 0]){
          text(fontsize: 40, text:"日本語の表示がずれてしまう場合があります")
        }
        stack(padding:[50, 0]){
          text(fontsize: 40, text:"これはJavaFXの問題なので、現状は文字列の後ろに\n　　適宜スペースを入れるなどして調節する仕様です…")
        }
      }
      bottom{}
    }
  }

  slide(alignment:"left",posx:100){
    text(text:"""エフェクトなどは使えませんが、
     最低限のプレゼン用の機能は実装されています""", fontsize: 40, posy:-50)
    text(text:"簡単なプレゼンであれば、できると思います", fontsize:40, posy:50)
  }

  slide(alignment:"topleft"){
    stack(padding:[130, 0, 0, 100], alignment: "topleft"){
      text(text:"""どちらかと言えば、一番問題なのは
表示するものが全てJavaFXのオブジェクトであることです...""", fontsize:35)
    }
    stack(padding:[100, 0, 0, 100], alignment: "topleft"){
      text(text:"""つまり大量にスライドやオブジェクトを作ると、
      メモリを大量消費するということです...""", fontsize:35)
    }
    stack(padding:[100, 0, 0, 100], alignment: "topleft"){
      text(text:"ただテストしてみましたが、スライド100枚に\nグラフを2個ずつのせる程度なら問題ないみたいです", fontsize:35)
    }
  }

    slide(alignment:"top"){
    border{
      top{vbox(background:"floralwhite"){text(text:"DSLについて", fontsize: 60)}}
      center(width:890, height:400){
        textarea(editable:false, column:15, row:14,
          text:"""import main.groovy.gpresentfx.GPresentBuilder

GPresentBuilder.dsl().slides(
  name:"プレゼンテーション",
  width:1024, height: 700,
  alignment:"center", background:"steelblue"){
  
  slide{
    text(text:"DSLで作れるプレゼン", fontsize: 90, textcolor:"floralwhite")
    text(text:"Groovy Presentation FX", textcolor:"red", fontsize: 50)
  }
}""")
      }
      bottom(alignment:"right"){
        text(text:"DSLファイルにimport文が必要なのが難点ですが、　　　 　", fontsize:30)
        text(text:"jarファイル一個で動きます　　　　", fontsize:30)
      }
    }
  }

  //slide(timebox())

  slide{
    vbox(padding:50){
      hbox{
        text(text:"さすがに先ほどのは", fontsize:40)
        text(text:"“できる”", fontsize:40, textcolor:"yellow")
        text(text:"というだけで", fontsize:40)
      }
      text(text:"目的としては少々異なっていて、単にできるだけです", fontsize:40)}
    vbox(padding:50){text(text:"""なのでDSLでのイベント処理などをサポートする
ようなことは今のところ考えていません""", fontsize:40)}
  }

  slide(alignment:"top"){
    border{
      top{
        vbox(background:"floralwhite"){text(text:"プラグイン機能", fontsize: 60)}
      }
      center(padding:[100, 0]){
        text(fontsize: 40, text:"起動時にプラグインスクリプトを読みこむことができます")
        text(fontsize: 35, text:"""
たとえば下の円のオブジェクトは外部プラグインスクリプトで
生成処理が記述されていて、それを用いています""")
        vbox(padding:[50,0]){redcircle{}}
      }
      bottom{}
    }
  }

  slide{
    hbox{
      text(text:"これが現状の")
      text(text:"Groovy Presentation FX", textcolor:"red")
     text(text:"です")
    }
  }

  slide{
    text(text:"終わり",fontsize:100)
  }
}