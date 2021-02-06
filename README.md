# 概要

AtCoder用リポジトリ

細かい補足などは以下に記載。  
https://masaki-blog.net/category/atcoder

# 構成
* main  
メイン

* template  
サンプルプログラム
* test  
テスト用のベースファイル


# 実装上の制限、補足

AtCoder（競技ブログラム）であるが為の制限、補足。  
通常のJava開発ではしないようが良い話。


## 入出力
Scannerなどだと少し遅いのでBufferedReaderやPrintWriterを使う。

```
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
PrintWriter pw = new PrintWriter(System.out);
```

## 配列
Listだとメモリを使う＆遅いので、配列を使う。  
俺は通常のJava開発とは真逆なので注意。
（通常のJava開発ならListを使うべき）





