package org.coronasurveys.mobile.utils

import android.content.Context

const val CONTACT_EMAIL = "coronasurveys@gmail.com"
const val INSTAGRAM = "https://www.instagram.com/coronasurveys"
const val TWITTER = "https://twitter.com/coronasurveys"
const val FACEBOOK = "https://www.facebook.com/coronasurveys"
const val GITHUB = "https://github.com/GCGImdea/coronasurveys/tree/master/data"
const val TEAM_URL = "https://coronasurveys.org/team"
const val PLOT_URL = "https://coronasurveys.org/grafana/d-solo/G_Aw4CrZk/coronasurveys?tab=advanced&panelId=20&orgId=1&from=1584576000000"
const val IMAGE_URL = "https://coronasurveys.org/assets/images/XX-appgraph.png"

const val CHANNEL_ID = "reminders"

fun imageUrl(context: Context) = IMAGE_URL.replace("XX", context.getCountry().alpha2, ignoreCase = true)