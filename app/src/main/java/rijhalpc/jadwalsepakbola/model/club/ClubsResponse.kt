package rijhalpc.jadwalsepakbola.model.club

import com.google.gson.annotations.SerializedName

data class ClubsResponse(
    @SerializedName("teams")
    var teams: List<Clubs>
)