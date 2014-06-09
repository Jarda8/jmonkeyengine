/*
 *  Copyright (c) 2009-2010 jMonkeyEngine
 *  All rights reserved.
 * 
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are
 *  met:
 * 
 *  * Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 *  * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 * 
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *  "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 *  TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *  PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 *  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.jme3.gde.core.sceneexplorer.nodes.actions.impl;

import com.jme3.asset.AssetManager;
import com.jme3.gde.core.sceneexplorer.nodes.actions.AbstractNewSpatialAction;
import com.jme3.gde.core.sceneexplorer.nodes.actions.NewGeometryAction;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;

/**
 *
 * @author david.bernard.31
 */
@org.openide.util.lookup.ServiceProvider(service = NewGeometryAction.class)
public class NewGeometrySphereAction extends AbstractNewSpatialAction implements NewGeometryAction {

    public NewGeometrySphereAction() {
        name = "Sphere";
    }

    @Override
    protected Spatial doCreateSpatial(Node parent) {
        Geometry geom = sphere(pm);
        parent.attachChild(geom);
        return geom;
    }
    
    static Material material(AssetManager assetManaget, NewGeometrySettings cfg) {
        Material mat = new Material(assetManaget, "Common/MatDefs/Misc/Unshaded.j3md");
        ColorRGBA  c = cfg.getMatRandom() ?ColorRGBA.randomColor() : cfg.getMatColor();
        mat.setColor("Color", c);
        return mat;
    }
    
    static Geometry sphere(AssetManager assetManager) {
        NewGeometrySettings cfg = new NewGeometrySettings();
        Sphere b = new Sphere(
            cfg.getSphereZSamples()
            , cfg.getSpherRadialSamples()
            , cfg.getSphereRadius()
            , cfg.getSphereUseEvenSlices()
            , cfg.getSphereInterior()
        );
        b.setMode(cfg.getSphereMode());
        Geometry geom = new Geometry(cfg.getSphereName(), b);
        geom.setMaterial(material(assetManager, cfg));        
        return geom;
    }

}
