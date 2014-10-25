# java-q

Hej folks! Again and again we have to interact with a [kdb+](http://kx.com/kdb+.php) and [kdb+tick](http://kx.com/kdb+tick.php). The guys from [KX Systems](http://kx.com/) provide us [c.java](http://code.kx.com/wsvn/code/kx/kdb%2B/c/kx/c.java) to interact with their product. You might experienced that c.java is not really using an object oriented style (because q does not?), so we started to write our own wrapper and called it java-q. Feel free to create [Issues](https://github.com/michaelwittig/java-q/issues) if something is missing for you. And now: have fun:) 

[Learn more about the Features](https://github.com/michaelwittig/java-q/wiki/Features) or continue to learn about our design goals and principles:

## Encapsulation

**You do not need to be a q god to use this library!**

We encapsulate all the q stuff for you! It's just simple Java. Look at the following example. Have you ever seen a [q-sql select](http://code.kx.com/wiki/JB:QforMortals/queries_q_sql) so Java pretty?

```java
Select select = trade.select()
	.column(size.sum())
	.column(price.avg())
	.group(sym.group())
	.group(time.xbar(LongValue.from(60000L)))
	.filter(sym.filterIn(SymbolValue.froms(new String[] {"AAA", "BBB"})))
	.order(Direction.descending, time)
	.build();
```

## Schema definition

Your schema is defined by code not by text! You get easy refactoring and lesser typos for free:)

[Learn more about how to define your schema with a few lines of code.](https://github.com/michaelwittig/java-q/wiki/HowTo:-Schema)

## Synchronous Access

### Queries using select

[Learn more about how to create a q query.](https://github.com/michaelwittig/java-q/wiki/HowTo:-Query)

```java
// get table schema
MyTable table = MyTable.get();

// create select query
Select select = table.select()
	.column(table.size().sum())
	.column(table.price().avg())
	.group(table.sym().group())
	.filter(table.sym().filterIn(SymbolValue.froms(new String[] {"AAA", "BBB"})))
	.order(Direction.descending, table.time())
	.build();
System.out.println("q: " + select.toQ());

// connect to kdb+
KXConnectorSync kx = KXConnectorFactory.create("localhost", 5011);
kx.connect();

// execute select query and print the result
Result result = kx.select(select);
for (TableRow row : table.read(result)) {
	System.out.println(row.get(table.time()));
	System.out.println(row.get(table.sym()));
	System.out.println(row.get(table.price()));
	System.out.println(row.get(table.size()));
}

// close connection
kx.disconnect();
```

## Asynchronous Access

### Subscription using .u.sub[]

[Learn more about how to subscribe to q kdb+tick environement.](https://github.com/michaelwittig/java-q/wiki/HowTo:-Subscription)
