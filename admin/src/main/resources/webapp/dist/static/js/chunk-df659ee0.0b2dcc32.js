(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-df659ee0"],{"18dd":function(t,e,n){"use strict";n.r(e);var l=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"app-container"},[n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],attrs:{data:t.list,"element-loading-text":"Loading",border:"",fit:"","highlight-current-row":""}},[n("el-table-column",{attrs:{align:"center",label:"ID",width:"150"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.$index)+" ")]}}])}),n("el-table-column",{attrs:{align:"center",label:"节点地址",width:"200"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.title)+" ")]}}])}),n("el-table-column",{attrs:{label:"节点ID",width:"200",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("span",[t._v(t._s(e.row.author))])]}}])}),n("el-table-column",{attrs:{label:"消费者列表",width:"200",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.pageviews)+" ")]}}])}),n("el-table-column",{attrs:{label:"提供者列表",width:"200",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.pageviews)+" ")]}}])}),n("el-table-column",{attrs:{label:"启动时间",width:"200",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.pageviews)+" ")]}}])}),n("el-table-column",{attrs:{label:"操作",width:"200",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.pageviews)+" ")]}}])})],1)],1)},a=[],i={filters:{statusFilter:function(t){var e={published:"success",draft:"gray",deleted:"danger"};return e[t]}},data:function(){return{list:null,listLoading:!1}},created:function(){this.fetchData()},methods:{fetchData:function(){}}},r=i,s=(n("3c26"),n("2877")),o=Object(s["a"])(r,l,a,!1,null,"d1188736",null);e["default"]=o.exports},"3c26":function(t,e,n){"use strict";n("d1d9")},d1d9:function(t,e,n){}}]);