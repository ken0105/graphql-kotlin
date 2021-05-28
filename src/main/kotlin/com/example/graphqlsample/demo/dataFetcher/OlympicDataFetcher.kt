package com.example.graphqlsample.demo

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class OlympicDataFetcher {
    @DgsQuery
    fun Olympics(@InputArgument yearFilter: Int?): List<Olympic> {
        return if (yearFilter != null) {
            olympics.filter { it.year == yearFilter }
        } else {
            olympics
        }
    }
}

    private val olympics = listOf(
        Olympic(1896, "アテネ（ギリシャ）"),
    Olympic(1900,	"パリ（フランス）"),
    Olympic(1904,	"セントルイス（アメリカ）"),
    Olympic(1908,	"ロンドン（イギリス）"),
    Olympic(1912,	"ストックホルム（スウェーデン）"),
    Olympic(1916,	"ベルリン（ドイツ）-"),
    Olympic(1920,	"アントワープ（ベルギー）"),
    Olympic(1924,	"パリ（フランス）"),
    Olympic(1928,	"アムステルダム（オランダ）"),
    Olympic(1932,	"ロサンゼルス（アメリカ）"),
    Olympic(1936,	"ベルリン（ドイツ）"),
    Olympic(1948,	"ロンドン（イギリス）"),
    Olympic(1952,	"ヘルシンキ（フィンランド）"),
    Olympic(1956,	"メルボルン（オーストラリア）"),
    Olympic(1960,	"ローマ（イタリア）"),
    Olympic(1964,	"東京（日本）"),
    Olympic(1968,	"メキシコシティー（メキシコ）"),
    Olympic(1972,	"ミュンヘン（西ドイツ）"),
    Olympic(1976,	"モントリオール（カナダ）"),
    Olympic(1980,	"モスクワ（ソ連）"),
    Olympic(1984,	"ロサンゼルス（アメリカ）"),
    Olympic(1988,	"ソウル（韓国）"),
    Olympic(1992,	"バルセロナ（スペイン）"),
    Olympic(1996,	"アトランタ（アメリカ）"),
    Olympic(2000,	"シドニー（オーストラリア）"),
    Olympic(2004,	"アテネ（ギリシャ）"),
    Olympic(2008,	"北京（中国）"),
    Olympic(2012,	"ロンドン（イギリス）"),
    Olympic(2016,	"リオデジャネイロ（ブラジル）"),
    Olympic(2020,	"東京（日本）"),
    Olympic(1924,	"シャモニー・モンブラン（フランス）"),
    Olympic(1928,	"サン・モリッツ（スイス）"),
    Olympic(1932,	"レークプラシッド（アメリカ）"),
    Olympic(1936,	"ガルミッシュ・パルテンキルヘン（ドイツ）"),
    Olympic(1948,	"サン・モリッツ（スイス）"),
    Olympic(1952,	"オスロ（ノルウェー）"),
    Olympic(1956,	"コルチナ・ダンペッツオ（イタリア）"),
    Olympic(1960,	"スコーバレー（アメリカ）"),
    Olympic(1964,	"インスブルック（オーストリア）"),
    Olympic(1968,	"グルノーブル（フランス）"),
    Olympic(1972,	"札幌（日本）"),
    Olympic(1976,	"インスブルック（オーストリア）"),
    Olympic(1980,	"レークプラシッド（アメリカ）"),
    Olympic(1984,	"サラエボ（ユーゴスラビア）"),
    Olympic(1988,	"カルガリー（カナダ）"),
    Olympic(1992,	"アルベールビル（フランス）"),
    Olympic(1994,	"リレハンメル（ノルウェー）"),
    Olympic(1998,	"長野（日本）"),
    Olympic(2002,	"ソルトレークシティー（アメリカ）"),
    Olympic(2006,	"トリノ（イタリア）"),
    Olympic(2010,	"バンクーバー（カナダ）"),
    Olympic(2014,	"ソチ（ロシア）"),
    Olympic(2018,	"平昌（韓国）"),
    Olympic(2022,	"北京（中国）")
    )


data class Olympic(
    val year: Int,
    val Country: String
)