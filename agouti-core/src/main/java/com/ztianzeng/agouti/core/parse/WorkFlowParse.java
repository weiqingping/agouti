/*
 * Copyright 2018-2019 zTianzeng Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ztianzeng.agouti.core.parse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ztianzeng.agouti.core.AgoutiException;
import com.ztianzeng.agouti.core.resource.AbstractResource;
import com.ztianzeng.agouti.core.resource.ClassPathResource;
import com.ztianzeng.common.workflow.WorkFlowDef;

import java.io.IOException;

/**
 * @author zhaotianzeng
 * @version V1.0
 * @date 2019-04-16 19:45
 */
public class WorkFlowParse {
    private WorkFlowParse() {

    }

    private final static ObjectMapper OM = new ObjectMapper();

    /**
     * load WorkFlowDef from class resource
     *
     * @param path resource path
     * @return
     */
    public static WorkFlowDef fromResource(String path) {
        AbstractResource resource = new ClassPathResource(
                path, ClassLoader.getSystemClassLoader());

        return WorkFlowParse.parse(resource);
    }


    public static WorkFlowDef fromText(String json) {
        try {
            return OM.readValue(json, WorkFlowDef.class);
        } catch (IOException e) {
            throw new AgoutiException(e);
        }
    }

    /**
     * read json file wrap to WorkFlowDef
     *
     * @param resource res
     * @return WorkFlowDef
     */
    public static WorkFlowDef parse(AbstractResource resource) {
        try {
            return OM.readValue(resource.read(), WorkFlowDef.class);
        } catch (IOException e) {
            throw new AgoutiException(e);
        }
    }
}