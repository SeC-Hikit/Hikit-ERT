package org.hikit.er.rest


data class EventDto(
    var id: String,
    var title: String,
    var description: String,
    var locations: List<EventLocationDto>,
    var date_from: String,
    var date_to: String,
    var ticketing: TicketDto,
    var category: List<CategoryDto>,
    var cityRef: CityRefDto,
    var attachments: List<ImageDto>
)