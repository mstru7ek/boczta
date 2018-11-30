package pl.dev.revelboot.boczta

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@RestController
class HistoryItemResource(val historyItemRepository: HistoryItemRepository) {


    @PostMapping("/api/history/url")
    fun saveUrl(@RequestHeader("X-Fumel-Client-Id") clientId: String, @RequestBody historyItem: HistoryItemRequest): ResponseEntity<Confirmation> {
        historyItemRepository.save(historyItem.mapToEntity(clientId))
        return ResponseEntity(Confirmation(true), HttpStatus.CREATED)
    }

    @GetMapping("/api/history/url/{clientId}")
    fun getUrlList(@PathVariable clientId: String, page: Pageable): ResponseEntity<Page<HistoryItem>> {
        return ResponseEntity(historyItemRepository.findAllByClientId(clientId, page), HttpStatus.CREATED)
    }

    data class Confirmation(val success: Boolean)
}

data class HistoryItemRequest(
        val id: String? = null,
        val url: String? = null,
        val title: String? = null,
        val lastVisitTime: Double = 0.0,
        val visitCount: Int = 0,
        val typedCount: Int = 0)

fun HistoryItemRequest.mapToEntity(clientId: String) : HistoryItem {
    return HistoryItem(
            clientId = clientId,
            id = id,
            url = url,
            title = title,
            lastVisitTime = lastVisitTime,
            visitCount = visitCount,
            typedCount = typedCount)
}

@Entity
data class HistoryItem(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var tid: Long? = null,
        val clientId: String? = null,
        val id: String? = null,
        val url: String? = null,
        val title: String? = null,
        val lastVisitTime: Double = 0.0,
        val visitCount: Int = 0,
        val typedCount: Int = 0)

@Repository
interface HistoryItemRepository : JpaRepository<HistoryItem, Long> {

    fun findAllByClientId(clientId: String , page: Pageable): Page<HistoryItem>
}
