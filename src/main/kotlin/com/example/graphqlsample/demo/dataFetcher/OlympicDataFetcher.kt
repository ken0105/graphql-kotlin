package com.example.graphqlsample.demo.dataFetcher

import com.example.graphqlsample.demo.dataFetcher.types.Olympic
import com.example.graphqlsample.demo.dataFetcher.types.Result
import com.example.graphqlsample.demo.dataFetcher.types.Status
import com.example.graphqlsample.demo.processor.Processor
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import graphql.schema.DataFetchingEnvironment
import org.reactivestreams.Publisher

@DgsComponent
class OlympicDataFetcher(private val processor: Processor<Olympic>) {
    @DgsQuery
    fun olympics(@InputArgument yearFilter: Int?): Result {
        val olympics = if (yearFilter != null) {
            olympics.filter { it.year == yearFilter }
        } else {
            olympics
        }
        return Result(Status(200, "OK"), olympics.toList())
    }

    @DgsData(parentType = "Mutation", field = "addOlympic")
    fun addOlympic(dataFetchingEnvironment: DataFetchingEnvironment): Result {
        val year = dataFetchingEnvironment.getArgument<Int>("year")
        val country = dataFetchingEnvironment.getArgument<String>("country")
        val newOlympic = Olympic(year, country)
        olympics.add(newOlympic)
        processor.emit(newOlympic)
        return Result(Status(200, "OK"), listOf(Olympic(year = year, country = country)))
    }

    @DgsData(parentType = "Mutation", field = "deleteOlympic")
    fun deleteOlympic(dataFetchingEnvironment: DataFetchingEnvironment): Result {
        val year = dataFetchingEnvironment.getArgument<Int>("year")

        olympics
            .find { it.year == year }
            ?.let { olympics.remove(it) }

        return Result(Status(200, "OK"))
    }

    @DgsData(parentType = "Subscription", field = "subscribeOlympics")
    fun subscribeOlympics(): Publisher<Olympic> {
        return processor.publish()
    }

}

    private val olympics = mutableSetOf(
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
