package com.youpin.model

import org.springframework.dao.DataIntegrityViolationException

class GoodsController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		params.orderBy = "new"
		params.sort = "id"
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		println params
		params.max = Math.min(max ?: 9, 36)
		[goodsInstanceList: Goods.findAllByForSale(true,
			params), goodsInstanceTotal: Goods.countByForSale(true),metaParams:params]
	}

	def _items(Integer max){
		println params
		params."metaParams.max" = Math.min(max ?: 9, 36)

		[goodsInstanceList: Goods.findAllByForSale(true,
			params."metaParams"), goodsInstanceTotal: Goods.countByForSale(true)]
	}
	
}
