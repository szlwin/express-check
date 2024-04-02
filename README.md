express-check
=====
可根据表达式单独对数据或对象中的数据进行复杂校验，可支持数字、字符、布尔、日期类型，另提供简单语法可支持赋值、计算以及简单逻辑判断。

此项目依赖于本人另一开源项目：https://github.com/szlwin/santr

JDK Version: 1.8.

License
=======
Copyright (c) 2014.<br>
Author: szlwin.

Licensed under the BSD lincese.


示例
=======
下面为几个示例，且示例都在test目录下

示例一<br>

    public static void testPropertyCheck_String() throws ExecuteExpection {
      PropertyCheck propertyCheck = new PropertyCheck();
      propertyCheck.setCheckValue("3");
      propertyCheck.setPattern("NOTNULL;NOTEMPTY;NOTEQUAL:'2';EQUAL:'3';LETTER:4;ELETTER:3;GREATER:2;EGREATER:3");
      System.out.println(propertyCheck.check());
    }

以上例子中，将字符串3进行验证，其必须为非空、非空字符串、不等于2等规则，其可将字符串转换为数字后，进行数据校验规则进行校验，其具体校验规则如下：<br>

<table>
  <tr>
    <td>规则</td>
    <td>含义</td>
    <td>数据类型</td>
    <td>备注</td>
  </tr>
  <tr>
    <td>NOTNULL</td>
    <td>不为空</td>
    <td>字符串、数字、布尔、日期、对象</td>
    <td></td>
  </tr>
  <tr>
    <td>NULL</td>
    <td>为空</td>
    <td>字符串、数字、布尔、日期、对象</td>
    <td></td>
  </tr>
  <tr>
    <td>EQUAL</td>
    <td>等于</td>
    <td>字符串、数字、布尔、日期</td>
    <td></td>
  </tr>
  <tr>
    <td>NOTEQUAL</td>
    <td>不等于</td>
    <td>字符串、数字、布尔、日期</td>
    <td></td>
  </tr>
  <tr>
    <td>NOTEMPTY</td>
    <td>不为空字符串</td>
    <td>字符串</td>
    <td></td>
  </tr>
  <tr>
    <td>EMPTY</td>
    <td>为空字符串</td>
    <td>字符串</td>
    <td></td>
  </tr>
  <tr>
    <td>MATCH</td>
    <td>匹配正则表达式</td>
    <td>字符串</td>
    <td></td>
  </tr>
  <tr>
    <td>UNMATCH</td>
    <td>不匹配正则表达式</td>
    <td>字符串</td>
    <td></td>
  </tr>
  <tr>
    <td>LETTER</td>
    <td>小于</td>
    <td>字符串、数字、日期</td>
    <td></td>
  </tr>
  <tr>
    <td>ELETTER</td>
    <td>小于等于</td>
    <td>字符串、数字、日期</td>
    <td></td>
  </tr>
  <tr>
    <td>GREATER</td>
    <td>大于</td>
    <td>字符串、数字、日期</td>
    <td></td>
  </tr>
  <tr>
    <td>EGREATER</td>
    <td>大于等于</td>
    <td>字符串、数字、日期</td>
    <td></td>
  </tr>
</table>
 
