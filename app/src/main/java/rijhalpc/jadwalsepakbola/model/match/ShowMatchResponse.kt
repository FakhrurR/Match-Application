package rijhalpc.jadwalsepakbola.model.match

import com.google.gson.annotations.SerializedName

data class ShowMatchResponse(
    @SerializedName("events")
    var events: List<ShowMatch>)