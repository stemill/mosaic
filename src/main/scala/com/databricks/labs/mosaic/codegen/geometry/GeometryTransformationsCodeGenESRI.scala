package com.databricks.labs.mosaic.codegen.geometry

import com.databricks.labs.mosaic.codegen.format.ConvertToCodeGen
import com.databricks.labs.mosaic.core.geometry.MosaicGeometryESRI
import com.databricks.labs.mosaic.core.geometry.api.GeometryAPI
import com.esri.core.geometry.ogc.OGCGeometry

import org.apache.spark.sql.catalyst.expressions.codegen.CodegenContext
import org.apache.spark.sql.types.DataType

object GeometryTransformationsCodeGenESRI {

    def simplify(ctx: CodegenContext, geomEval: String, toleranceEval: String, dataType: DataType, geometryAPI: GeometryAPI): (String, String) = {
        val (inCode, geomInRef) = ConvertToCodeGen.readGeometryCode(ctx, geomEval, dataType, geometryAPI)
        val tmpGeom = ctx.freshName("tmpGeom")
        val (outCode, geomOutRef) = ConvertToCodeGen.writeGeometryCode(ctx, tmpGeom, dataType, geometryAPI)
        val ogcGeometryClass = classOf[OGCGeometry].getName
        val mosaicGeometryOGCClass = classOf[MosaicGeometryESRI].getName
        (
          s"""
             |$inCode
             |$ogcGeometryClass $tmpGeom = (($mosaicGeometryOGCClass)$mosaicGeometryOGCClass.apply($geomInRef).rotate($toleranceEval)).getGeom();
             |$outCode
             |""".stripMargin,
          geomOutRef
        )
    }

    def rotate(ctx: CodegenContext, geomEval: String, angleEval: String, dataType: DataType, geometryAPI: GeometryAPI): (String, String) = {
        val (inCode, geomInRef) = ConvertToCodeGen.readGeometryCode(ctx, geomEval, dataType, geometryAPI)
        val tmpGeom = ctx.freshName("tmpGeom")
        val (outCode, geomOutRef) = ConvertToCodeGen.writeGeometryCode(ctx, tmpGeom, dataType, geometryAPI)
        val ogcGeometryClass = classOf[OGCGeometry].getName
        val mosaicGeometryOGCClass = classOf[MosaicGeometryESRI].getName
        (
          s"""
             |$inCode
             |$ogcGeometryClass $tmpGeom = (($mosaicGeometryOGCClass)$mosaicGeometryOGCClass.apply($geomInRef).rotate($angleEval)).getGeom();
             |$outCode
             |""".stripMargin,
          geomOutRef
        )
    }

    def scale(
        ctx: CodegenContext,
        geomEval: String,
        xDist: String,
        yDist: String,
        dataType: DataType,
        geometryAPI: GeometryAPI
    ): (String, String) = {
        val (inCode, geomInRef) = ConvertToCodeGen.readGeometryCode(ctx, geomEval, dataType, geometryAPI)
        val tmpGeom = ctx.freshName("tmpGeom")
        val (outCode, geomOutRef) = ConvertToCodeGen.writeGeometryCode(ctx, tmpGeom, dataType, geometryAPI)
        val ogcGeometryClass = classOf[OGCGeometry].getName
        val mosaicGeometryOGCClass = classOf[MosaicGeometryESRI].getName
        (
          s"""
             |$inCode
             |$ogcGeometryClass $tmpGeom = (($mosaicGeometryOGCClass)$mosaicGeometryOGCClass.apply($geomInRef).scale($xDist, $yDist)).getGeom();
             |$outCode
             |""".stripMargin,
          geomOutRef
        )
    }

    def translate(
        ctx: CodegenContext,
        geomEval: String,
        xDist: String,
        yDist: String,
        dataType: DataType,
        geometryAPI: GeometryAPI
    ): (String, String) = {
        val (inCode, geomInRef) = ConvertToCodeGen.readGeometryCode(ctx, geomEval, dataType, geometryAPI)
        val tmpGeom = ctx.freshName("tmpGeom")
        val (outCode, geomOutRef) = ConvertToCodeGen.writeGeometryCode(ctx, tmpGeom, dataType, geometryAPI)
        val ogcGeometryClass = classOf[OGCGeometry].getName
        val mosaicGeometryOGCClass = classOf[MosaicGeometryESRI].getName
        (
          s"""
             |$inCode
             |$ogcGeometryClass $tmpGeom = (($mosaicGeometryOGCClass)$mosaicGeometryOGCClass.apply($geomInRef).translate($xDist, $yDist)).getGeom();
             |$outCode
             |""".stripMargin,
          geomOutRef
        )
    }

}
