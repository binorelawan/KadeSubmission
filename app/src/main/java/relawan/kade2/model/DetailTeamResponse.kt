package relawan.kade2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailTeamResponse(
    val teams: List<DetailTeam>
) : Parcelable