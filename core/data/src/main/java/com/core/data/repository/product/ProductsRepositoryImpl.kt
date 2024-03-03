package com.core.data.repository.product

//class ProductsRepositoryImpl @Inject constructor(
//    private val service: ProductsService,
//    private val responseHandler: HandleRetrofitResponse
//) : ProductRepository {
//    override suspend fun getProducts(): Flow<Resource<List<GetProduct>>> {
//        return responseHandler.apiCall {
//            service.getProducts()
//        }.asResource {
//            it.map { productDto ->
//                productDto.toDomainModel()
//            }
//        }
//    }
//
//}