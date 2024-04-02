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

以上例子中(在TestCheck.java中)，将字符串3进行验证，其必须为非空、非空字符串、不等于2等规则，其可将字符串转换为数字后，进行数据校验规则进行校验，其具体校验规则如下：<br>

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


示例二<br>
以下为一个多条件、多数据校验例子<br>

    public static void testPattenCheck_1() throws ExecuteExpection {

		PatternCheck pattenCheck = new PatternCheck();
		
		Map<String,Object> map = new HashMap<String,Object>(20);
		map.put("productCount", 10);
		map.put("totalPrice", 20);
		map.put("userId", "2");
		pattenCheck.setPattern("( productCount > 0 or totalPrice >= 0 ) or ( productCount*(totalPrice+10) > 200 and userId != '1' )");
		pattenCheck.setCheckValue(map);
		System.out.println("testPattenCheck_1:"+pattenCheck.check());
  		System.out.println(simpleExprVisitor.getError().getCode());
	}

以上例子中，同时对数字及字符串类型的数据进行校验，且包含数字运算及复杂判断逻辑，其具体符号如下：<br>
<table>
  <tr>
    <td>符号</td>
    <td>含义</td>
    <td>备注</td>
  </tr>
  <tr>
    <td>and</td>
    <td>与</td>
    <td></td>
  </tr>
  <tr>
    <td>or</td>
    <td>或</td>
    <td></td>
  </tr>
  <tr>
    <td>+</td>
    <td>加运算符</td>
    <td></td>
  </tr>
  <tr>
    <td>-</td>
    <td>减运算符号</td>
    <td></td>
  </tr>
  <tr>
    <td>*</td>
    <td>乘运算符和</td>
    <td></td>
  </tr>
  <tr>
    <td>/</td>
    <td>除运算符号</td>
    <td></td>
  </tr>
  <tr>
    <td>=</td>
    <td>等于</td>
    <td></td>
  </tr>
  <tr>
    <td>!=</td>
    <td>不等于</td>
    <td></td>
  </tr>
  <tr>
    <td>!</td>
    <td>非</td>
    <td></td>
  </tr>
  <tr>
    <td>></td>
    <td>大于</td>
    <td></td>
  </tr>
  <tr>
    <td>>=</td>
    <td>大于等于</td>
    <td></td>
  </tr>
  <tr>
    <td><</td>
    <td>小于</td>
    <td></td>
  </tr>
  <tr>
    <td><=</td>
    <td>小于等于</td>
    <td></td>
  </tr>
  <tr>
    <td><=</td>
    <td>小于等于</td>
    <td></td>
  </tr>
</table>

示例三<br>
以下为一个将对象做为参数的例子<br>

	public void testObj() throws ExecuteExpection {

		TestCheck.Order order = new TestCheck.Order();

		TestCheck.Order order1 = new TestCheck.Order();
		order1.setId(1);
		order.setOrder(order1);
		PatternCheck pattenCheck = new PatternCheck();
		pattenCheck.setCheckValue(order);
		pattenCheck.setPattern("order.id=1");
		System.out.println(pattenCheck.check());
	}

示例四<br>
以下为一个语法例子，其可赋值、计算，并可进行简单逻辑处理<br>

     public static void main(String args[]) {
        SimpleExprParam simpleExprParam = new SimpleExprParam();
        Map<String, Object> map = new HashMap<>();
        map.put("totalPrice",350);
        simpleExprParam.setParamMap(map);
        simpleExprParam.setExternalMap(map);
        SimpleExprVisitor simpleExprVisitor = new SimpleExprVisitor();
        simpleExprVisitor.setParamer(simpleExprParam);
        ExpressParser lexerExecuter = new ExpressParser();
        lexerExecuter.addVisitor(simpleExprVisitor);
        try {
            lexerExecuter.parser("SimpleExpr",
                    "#num : if !(totalPrice>10) then totalPrice*1.1  else totalPrice*1.2;\n" +
                          "#num : if (#num>10 and totalPrice>10) then #num*1.1;\n" +
                    "totalPrice : #num*totalPrice;\n"+
                    "error('1000','totalPrice error') !: totalPrice >10 or #num>10;");
        } catch (ExecuteInvaildException e) {
            e.printStackTrace();
        }
        System.out.println(map.get("totalPrice"));
	//显示错误信息
        System.out.println(simpleExprVisitor.getError().getCode());
    }
以上例子中“:”符号左边为接受赋值的变量，右边为计算公式，其#开头表示为临时变量(如#num)，与参数进行区分，“;”表示一个语句结束。
其最后一行“error”语句，表示如右边判断为false，则会生成一个error信息('!:'如换成':'则表示右边判断为true时会产生error)，其'1000'为错误编码，'totalPrice error'为错误信息。如存在校验错误，则可通过simpleExprVisitor.getError()获取。
完整语法为：<br>

	#num : if !(totalPrice>10) then totalPrice*1.1  else totalPrice*1.2;
	#num : if (#num>10 and totalPrice>10) then #num*1.1;
	totalPrice : #num*totalPrice;
	error('1000','totalPrice error') !: totalPrice >10 or #num>10;
 如存在计算、校验、赋值等多种逻辑情况下，可用以上方式，根据实际情况定义语句。
