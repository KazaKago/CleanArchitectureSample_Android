package com.kazakago.cleanarchitecture.domain.model.weather

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Image Model
 *
 * Created by tamura_k on 2016/06/03.
 */
@PaperParcel
class ImageModel : PaperParcelable {

    //天気（晴れ、曇り、雨など）
    var title: String? = null
    //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
    var link: String? = null
    //天気アイコンのURL
    var url: String? = null
    //天気アイコンの幅
    var width: Int? = null
    //天気アイコンの高さ
    var height: Int? = null

    companion object {
        @JvmField
        val CREATOR = PaperParcelImageModel.CREATOR
    }

}
