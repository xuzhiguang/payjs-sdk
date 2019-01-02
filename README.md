## payjs-sdk

PAYJS 旨在解决需要使用交易数据流的个人、创业者、个体户等小微支付需求，帮助开发者使想法快速转变为原型

PAYJS 只负责信息流，不接管资金流。资金无任何风险

官方网站：https://payjs.cn


### sdk使用

> 初始化payJs类
```java
PayJs payJs = new PayJs(mchid, key);

```
> 收银台支付
```java
String payUrl = payJs.casher(request);
```

> 具体使用方法请看单元测试

使用邀请码会获得奖励

我的邀请码：https://payjs.cn/ref/ZEQBVZ