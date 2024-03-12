package com.core.data.repository.category

//class LeaguesRepositoryImpl @Inject constructor(
//    private val service: LeaguesService,
//    private val responseHandler: HandleRetrofitResponse
//) : LeagueRepository {
//    override suspend fun getLeagues(): Flow<Resource<List<GetLeague>>> {
//        return responseHandler.apiCall {
//            service.getLeagues()
//        }.asResource {
//            it.map { dto ->
//                dto.toDomainModel()
//            }
//        }
//    }
//
//    override suspend fun getSeriesBySlug(slug: String): Flow<Resource<List<GetSeries>>> {
//        return responseHandler.apiCall {
//            service.getSeriesBySlug(slug)
//        }.asResource {
//            it.map { seriesDto ->
//                seriesDto.toDomainModel()
//            }
//        }
//    }
//}