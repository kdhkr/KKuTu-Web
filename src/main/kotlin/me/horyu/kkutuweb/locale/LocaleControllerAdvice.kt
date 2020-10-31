package me.horyu.kkutuweb.locale

import me.horyu.kkutuweb.shop.ShopService
import me.horyu.kkutuweb.shop.response.ResponseGoodDetail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.servlet.support.RequestContextUtils
import java.util.*
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class LocaleControllerAdvice(
        @Autowired private val localePropertyLoader: LocalePropertyLoader,
        @Autowired private val shopService: ShopService
) {
    @ModelAttribute("language")
    fun getLanguageCode(request: HttpServletRequest): String {
        return getLocale(request).language
    }

    @ModelAttribute("messages")
    fun getMessages(request: HttpServletRequest): Map<String, String> {
        return localePropertyLoader.getMessages(getLocale(request))
    }

    @ModelAttribute("goodDetails")
    fun getGoodDetails(request: HttpServletRequest): Map<String, ResponseGoodDetail> {
        return shopService.getGoodDetails()
    }

    private fun getLocale(request: HttpServletRequest): Locale {
        return RequestContextUtils.getLocale(request)
    }
}