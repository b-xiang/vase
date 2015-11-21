//
// Copyright (c) 2011, chunquedong
// Licensed under the Academic Free License version 3.0
//
// History:
//   2011-7-4  Jed Young  Creation
//

using concurrent
using fanvasGraphics
using fanvasWindow
using fanvasGui
//using fanvasFwt
using fanvasAndroid

**
** Win Test
**
@Js
class WinTest
{

  static Void main() {
    ToolkitEnv.init
    WinTest().build.show
  }

  RootView build()
  {
    view := RootView
    {
        LinearLayout
        {
          padding = Insets(dpToPixel(40f))
          spacing = dpToPixel(15f)
          it.layoutParam.width = LayoutParam.matchParent

          Button { id = "button"; text = "Hello Button" },
          ComboBox {
            it.items = ["comboBox1","comboBox2","comboBox3","comboBox4"]
            selectedIndex = 0
          },
          Label { id = "label"; text = "Label"; },
          //ImageView { id = "image";  image = ConstImage(`fan://icons/x16/folder.png`) },

          Switch { text = "switch"; it.layoutParam.width = LayoutParam.matchParent },
          ToggleButton { text = "checkBox"; it.layoutParam.width = LayoutParam.matchParent },
          RadioButton { text = "radio1"; it.layoutParam.width = LayoutParam.matchParent },
          RadioButton { text = "radio2"; it.layoutParam.width = LayoutParam.matchParent },
          TextField { hint = "hint" },
        },
    }

    Button btn := view.findById("button")
    btn.onAction.add
    {
      MessageBox { it.label.text = "hello world" }.show(view)
    }

    return view
  }

}