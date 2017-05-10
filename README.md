# thoughtworks

-
符号表示:	

     X 表示一个 strike
     / 表示一个 spare
     - 表示一个 miss
     | 表示一格的分界线
     || 之后的字符表示最后一格的额外机会

 
规则总结:
	
	* 一共10 个frame由 | 隔开。
	* 最后一个frame后面由||隔开表示额外机会。
	* 每个frame中，第一次就击倒10个球，X 对应10分，加上接下来两球分数和
	* 每个frame中，两个球击倒10个球，/ 对应10分，加上接下来一球分数和。
	* 每个frame中，两个球没全部击倒，分数为击倒的分数。
	* 最后frame中，X 得到额外两次机会；/ 得到额外一次发球机会，计算击倒分数。

-
###程序分析
1. 字符串处理。
2. 采用循环一次读入每个frame的情况
3. 每一个frame的分数与下一个frame相关，需要记录当前frame的情况。
4. 最后一个frame的处理

# github参考资料

* [try git](https://try.github.io/levels/1/challenges/1)
* Git 参考手册 [网站](http://gitref.org/zh/index.html)
* github 用法 [网站](https://guides.github.com/activities/hello-world/)

		