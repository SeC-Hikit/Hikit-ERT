package org.hikit.er.data.mapper

import org.bson.Document
import org.hikit.common.data.mapper.EntityMapper
import org.hikit.er.data.CityRef
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RefCityMapper @Autowired constructor(private val iatMapper: IatMapper) : EntityMapper<CityRef> {

    override fun mapToObject(doc: Document): CityRef =
        CityRef(
            istat = doc.getString(CityRef.ISTAT),
            city = doc.getString(CityRef.CITY),
            province = doc.getString(CityRef.PROVINCE),
            province_short = doc.getString(CityRef.PROVINCE_SHORT),
            iat = doc.getList(CityRef, Document::class.java, emptyList()).map { iatMapper.mapToObject(it) }
        )

    override fun mapToDocument(obj: CityRef): Document =
        Document(CityRef.ISTAT, obj.istat)
            .append(CityRef.CITY, obj.city)
            .append(CityRef.PROVINCE, obj.province)
            .append(CityRef.PROVINCE_SHORT, obj.province_short)
            .append(CityRef.IAT, obj.iat.map { iatMapper.mapToDocument(it) })

}