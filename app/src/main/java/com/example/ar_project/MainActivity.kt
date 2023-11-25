package com.example.ar_project

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.rendering.HeadlessEngineWrapper.TAG
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode


class MainActivity : AppCompatActivity() {

    private var renderableSource: RenderableSource? = null
    private lateinit var arFragment: ArFragment
    private lateinit var myRenderable: ModelRenderable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arFragment = supportFragmentManager.findFragmentById(R.id.ux_fragment) as ArFragment

        loadModel()

        arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
            val anchorNode = AnchorNode(hitResult.createAnchor())
            anchorNode.setParent(arFragment.arSceneView.scene)
            val transformableNode = TransformableNode(arFragment.transformationSystem)
            transformableNode.renderable = myRenderable
            transformableNode.setParent(anchorNode)
            transformableNode.select()
        }
    }

    private fun loadModel() {
        val modelUri = "file:///android_assets/Car_model.glb"

        renderableSource = RenderableSource.builder()
            .setSource(this, Uri.parse(modelUri), RenderableSource.SourceType.GLB)
            .setRecenterMode(RenderableSource.RecenterMode.ROOT)
            .build()

        buildModel()
    }

    private fun buildModel() {
        ModelRenderable.builder()
            .setSource(this, renderableSource)
            .build()
            .thenAccept { renderable -> myRenderable = renderable }
            .exceptionally { throwable ->
                Log.e(TAG, "Unable to load Renderable.", throwable)
                null
            }
    }
}




