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


# 小技
## 読み込み
スペース区切りの整数の取得。  
先に１行すべて取得してからのsplitする方が分かりやすいが、メモリなどが不安なので。

```
private int readInt(BufferedReader br) throws IOException {
    int a = 0;
    while (true) {
        int read = br.read();
        if (48 <= read && read <= 57) {
            a = a * 10 + read - 48;
        } else {
            return a;
        }
    }
}
```

## ソート
バケツソート。  
一度Listに詰めてソートの方が良いが、メモリなどが不安なので。

```
void sort() {
    int[] bucket = new int[101];
    for (int a : an) {
        bucket[a] += 1;
    }
    int index = 0;
    for (int i = 0; i < bucket.length; i++) {
        for (int j = 0; j < bucket[i]; j++) {
            an[index] = i;
            index++;
        }
    }
}
 ```


