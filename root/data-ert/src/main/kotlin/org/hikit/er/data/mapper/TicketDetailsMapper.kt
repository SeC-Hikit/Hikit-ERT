package org.hikit.er.data.mapper

import org.bson.Document
import org.hikit.common.data.mapper.EntityMapper
import org.hikit.er.data.Ticket
import org.springframework.stereotype.Component

@Component
class TicketDetailsMapper : EntityMapper<Ticket> {
    override fun mapToObject(doc: Document?): Ticket =
        Ticket(
            website = if (doc != null) doc.getString(Ticket.WEBSITE) else "",
            subscriptions = if (doc != null) doc.getString(Ticket.SUBSCRIPTIONS) else "",
            full_rate = if (doc != null) doc.getString(Ticket.FULL_RATE) else "",
            gratuity = if (doc != null) doc.getString(Ticket.GRATUITY) else "",
            type = if (doc != null) doc.getInteger(Ticket.TYPE) else 0,
            entrance = if (doc != null) doc.getString(Ticket.ENTRANCE) else ""
        )

    override fun mapToDocument(obj: Ticket): Document =
        Document()
            .append(Ticket.WEBSITE, obj.website)
            .append(Ticket.SUBSCRIPTIONS, obj.subscriptions)
            .append(Ticket.FULL_RATE, obj.full_rate)
            .append(Ticket.GRATUITY, obj.gratuity)
            .append(Ticket.TYPE, obj.type)
            .append(Ticket.ENTRANCE, obj.entrance)
}
