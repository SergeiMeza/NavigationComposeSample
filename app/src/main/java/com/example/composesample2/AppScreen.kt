package com.example.composesample2

sealed class AppScreen(val route: String, val title: String) {
    object Home: AppScreen("Home", "ホーム")
    object Teams: AppScreen("Teams", "チーム")
    object Me: AppScreen("Me", "プロフィール")

    object Calendar: AppScreen("Team/{teamId}/Calendar", "カレンダー")
    object Notes: AppScreen("Team/{teamId}/Notes", "ノート")
    object Homeworks: AppScreen("Team/{teamId}/Homeworks", "宿題")
    object PracticeSessions: AppScreen("Team/{teamId}/PracticeSessions", "練習スケジュール")
    object Workplace: AppScreen("Team/{teamId}/Workplace", "ワークプレイス")

    object Settings: AppScreen("Settings", "設定")
    object Team: AppScreen("Team", "チーム")
    object Profile: AppScreen("Profile", "プロフィール")
}