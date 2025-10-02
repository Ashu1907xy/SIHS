package com.example.smart.MultiLingual


object LocalizationHelper {
    fun getAppName(languageCode: String): String = when (languageCode) {
        "hi" -> "किशन साथी"
        "ta" -> "கிஷன் சதி\n"
        "pa" -> "ਕਿਸ਼ਨ ਸਾਥੀ"
        "mr" -> "किशन साठी"
        else -> "Kishan Sathi"
    }


    fun getDrawerItemName(languageCode: String, itemId: Int): String {
        return when (languageCode) {
            "en" -> getEnglishDrawerItem(itemId)
            "hi" -> getHindiDrawerItem(itemId)
            "mr" -> getMarathiDrawerItem(itemId)
            "pa" -> getPunjabiDrawerItem(itemId)
            "ta" -> getTamilDrawerItem(itemId)
            else -> getEnglishDrawerItem(itemId)
        }
    }
    private fun getMarathiDrawerItem(itemId: Int): String {
        return when (itemId) {
            1 -> "प्रोफाइल"
            2 -> "इनबॉक्स"
            3 -> "माझे शेत"
            4 -> "विश्लेषण"
            5 -> "आवडते"
            6 -> "मदत आणि FAQ"
            7 -> "बद्दल"
            8 -> "सेटिंग्ज"
            9 -> "लॉग आउट"
            else -> "अज्ञात"
        }
    }

    private fun getEnglishDrawerItem(itemId: Int): String {
        return when (itemId) {
            1 -> "Profile"
            2 -> "Inbox"
            3 -> "My Farm"
            4 -> "Analytics"
            5 -> "Favorite"
            6 -> "Help & FAQ"
            7 -> "About"
            8 -> "Settings"
            9 -> "Logout"
            else -> "Unknown"
        }
    }


    private fun getHindiDrawerItem(itemId: Int): String {
        return when (itemId) {
            1 -> "प्रोफाइल"
            2 -> "इनबॉक्स"
            3 -> "मेरा खेत"
            4 -> "विश्लेषण"
            5 -> "पसंदीदा"
            6 -> "सहायता और FAQ"
            7 -> "के बारे में"
            8 -> "सेटिंग्स"
            9 -> "लॉग आउट"
            else -> "अज्ञात"
        }
    }


    private fun getPunjabiDrawerItem(itemId: Int): String {
        return when (itemId) {
            1 -> "ਪ੍ਰੋਫਾਈਲ"
            2 -> "ਇਨਬਾਕਸ"
            3 -> "ਮੇਰਾ ਖੇਤ"
            4 -> "ਵਿਸ਼ਲੇਸ਼ਣ"
            5 -> "ਪਸੰਦੀਦਾ"
            6 -> "ਮਦਦ ਅਤੇ FAQ"
            7 -> "ਬਾਰੇ"
            8 -> "ਸੈਟਿੰਗਜ਼"
            9 -> "ਲੌਗ ਆਉਟ"
            else -> "ਅਗਿਆਤ"
        }
    }

    private fun getTamilDrawerItem(itemId: Int): String {
        return when (itemId) {
            1 -> "சுயவிவரம்"
            2 -> "இன்பாக்ஸ்"
            3 -> "என் பண்ணை"
            4 -> "பகுப்பாய்வு"
            5 -> "பிடித்தவை"
            6 -> "உதவி & FAQ"
            7 -> "பற்றி"
            8 -> "அமைப்புகள்"
            9 -> "வெளியேறு"
            else -> "தெரியாது"
        }
    }




    //



    fun getFeatureName(languageCode: String, featureId: Int): String {
        return when (languageCode) {
            "hi" -> getHindiFeatureName(featureId)
            "en" -> getEnglishFeatureName(featureId)
            "mr" -> getMarathiFeatureName(featureId)
            "pa" -> getPunjabiFeatureName(featureId)
            "ta" -> getTamilFeatureName(featureId)
            else -> getEnglishFeatureName(featureId)
        }
    }

    private fun getEnglishFeatureName(featureId: Int): String {
        return when (featureId) {
            1 -> "Weather"
            2 -> "Soil Test"
            3 -> "Soil Report"
            4 -> "AI Crop Advisor"
            5 -> "Support"
            6 -> "Market Price"
            7 -> "Camera"
            8 -> "News"
            else -> "Unknown"
        }
    }

    private fun getTamilFeatureName(featureId: Int): String {
        return when (featureId) {
            1 -> "வானிலை"
            2 -> "மண் பரிசோதனை"
            3 -> "மண் அறிக்கை\n"
            4 -> "AI பயிர் ஆலோசகர்"
            5 -> "ஆதரவு"
            6 -> "சந்தை விலை\n"
            7 -> "கேமரா"
            8 -> "செய்தி"
            else -> "தெரியவில்லை"
        }
    }

    private fun getPunjabiFeatureName(featureId: Int): String {
        return when (featureId) {
            1 -> "ਮੌਸਮ"
            2 -> "ਮਿੱਟੀ ਟੈਸਟ"
            3 -> "ਮਿੱਟੀ ਦੀ ਰਿਪੋਰਟ\n"
            4 -> "Aਏਆਈ ਫਸਲ ਸਲਾਹਕਾਰ\n"
            5 -> "ਸਪੋਰਟ"
            6 -> "ਮਾਰਕੀਟ ਕੀਮਤ\n"
            7 -> "ਕੈਮਰਾ"
            8 -> "ਖ਼ਬਰਾਂ"
            else -> "ਅਗਿਆਤ"
        }
    }


    private fun getHindiFeatureName(featureId: Int): String {
        return when (featureId) {
            1 -> "मौसम"
            2 -> "मिट्टी परीक्षण"
            3 -> "मिट्टी रिपोर्ट"
            4 -> "एआई फसल सलाहकार"
            5 -> "सहायता"
            6 -> "बाजार मूल्य"
            7 -> "कैमरा"
            8 -> "समाचार"
            else -> "अज्ञात"
        }
    }

    private fun getMarathiFeatureName(featureId: Int): String {
        return when (featureId) {
            1 -> "हवामान"
            2 -> "माती चाचणी"
            3 -> "माती अहवाल"
            4 -> "एआय पीक सल्लागार"
            5 -> "समर्थन"
            6 -> "बाजार किंमत"
            7 -> "कॅमेरा"
            8 -> "बातम्या"
            else -> "अज्ञात"
        }
    }

    // Optional: For badges
    fun getBadgeText(languageCode: String, badgeKey: String): String {
        return when (languageCode) {
            "en" -> getEnglishBadge(badgeKey)
            "hi" -> getHindiBadge(badgeKey)
            "mr" -> getMarathiBadge(badgeKey)
            "ta" -> getTamilBadge(badgeKey)
            "pa" -> getPunjabiBadge(badgeKey)
            else -> getEnglishBadge(badgeKey)
        }
    }

    private fun getEnglishBadge(badgeKey: String): String {
        return when (badgeKey) {
            "live" -> "Live"
            "smart" -> "Smart"
            "new_3" -> "3 New"
            "new_5" -> "5 New"
            "updated" -> "Updated"
            else -> badgeKey
        }
    }

    private fun getTamilBadge(badgeKey: String): String {
        return when (badgeKey) {
            "live" -> "நேரலை"
            "smart" -> "ஸ்மார்ட்"
            "new_3" -> "3 புதியவை"
            "new_5" -> "5 புதியவை"
            "updated" -> "புதுப்பிக்கப்பட்டது"
            else -> badgeKey
        }
    }


    private fun getPunjabiBadge(badgeKey: String): String {
        return when (badgeKey) {
            "live" -> "ਲਾਈਵ"
            "smart" -> "ਸਮਾਰਟ"
            "new_3" -> "3 ਨਵੇਂ"
            "new_5" -> "5 ਨਵੇਂ"
            "updated" -> "ਅਪਡੇਟ"
            else -> badgeKey
        }
    }

    private fun getHindiBadge(badgeKey: String): String {
        return when (badgeKey) {
            "live" -> "लाइव"
            "smart" -> "स्मार्ट"
            "new_3" -> "3 नए"
            "new_5" -> "5 नए"
            "updated" -> "अपडेट"
            else -> badgeKey
        }
    }

    private fun getMarathiBadge(badgeKey: String): String {
        return when (badgeKey) {
            "live" -> "लाइव्ह"
            "smart" -> "स्मार्ट"
            "new_3" -> "3 नवीन"
            "new_5" -> "5 नवीन"
            "updated" -> "अद्यतनित"
            else -> badgeKey
        }
    }


    //    fun getLikesText(count: Int, languageCode: String): String = when (languageCode) {
//        "hi" -> "$count पसंद"
//        "fr" -> "$count j'aime"
//        else -> "$count likes"
//    }
//
    fun getCommentsText(languageCode: String): String = when (languageCode) {
        "hi" -> "सभी टिप्पणियां देखें"
        "fr" -> "Voir les commentaires"
        "pa" -> "ਸਾਰੀਆਂ ਟਿੱਪਣੀਆਂ ਦੇਖੋ"
        else -> "View all comments"
    }
//
//    fun getLikeButtonText(languageCode: String) = when (languageCode) {
//        "hi" -> "पसंद"
//        "fr" -> "J'aime"
//        else -> "Like"
//    }
//
//    fun getCommentButtonText(languageCode: String) = when (languageCode) {
//        "hi" -> "टिप्पणी"
//        "fr" -> "Commenter"
//        else -> "Comment"
//    }
//
//    fun getShareButtonText(languageCode: String) = when (languageCode) {
//        "hi" -> "साझा करें"
//        "fr" -> "Partager"
//        else -> "Share"
//    }
//
//    fun getSaveButtonText(languageCode: String) = when (languageCode) {
//        "hi" -> "सेव करें"
//        "fr" -> "Enregistrer"
//        else -> "Save"
//    }
}
