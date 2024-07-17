/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shenyu.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.shenyu.admin.model.entity.NamespacePluginRelDO;
import org.apache.shenyu.admin.model.query.NamespacePluginQuery;
import org.apache.shenyu.admin.model.query.NamespacePluginQueryCondition;
import org.apache.shenyu.admin.model.query.PluginQuery;
import org.apache.shenyu.admin.model.vo.NamespacePluginVO;
import org.apache.shenyu.admin.model.vo.PluginVO;
import org.apache.shenyu.admin.validation.ExistProvider;

import java.io.Serializable;
import java.util.List;

/**
 * NamespacePluginRelMapper.
 */
@Mapper
public interface NamespacePluginRelMapper extends ExistProvider {

    /**
     * existed.
     *
     * @param id id
     * @return existed
     */
    @Override
    Boolean existed(@Param("id") Serializable id);


    /**
     * Batch save int.
     *
     * @param namespacePluginRelDOList the pluginNsRel do list
     * @return the int
     */
    int batchSave(@Param("namespacePluginRelDOList") List<NamespacePluginRelDO> namespacePluginRelDOList);

    /**
     * select plugin by query.
     *
     * @param namespacePluginQuery {@linkplain PluginQuery}
     * @return {@linkplain List}
     */
    List<NamespacePluginVO> selectByQuery(NamespacePluginQuery namespacePluginQuery);

    /**
     * select plugin by namespacePluginId.
     *
     * @param id    primary key.
     * @param namespaceId namespace id.
     * @return {@linkplain PluginVO}
     */
    NamespacePluginVO selectById(String id, String namespaceId);

    /**
     * select plugin by pluginId.
     *
     * @param pluginId    primary key.
     * @param namespaceId namespace id.
     * @return {@linkplain PluginVO}
     */
    NamespacePluginVO selectByPluginId(String pluginId, String namespaceId);

    /**
     * search by condition.
     *
     * @param condition condition.
     * @return list
     */
    List<NamespacePluginVO> searchByCondition(@Param("condition") NamespacePluginQueryCondition condition);

    /**
     * plugin existed.
     *
     * @param name    name
     * @param exclude exclude
     * @param namespaceId namespace id.
     * @return existed
     */
    Boolean nameExistedExclude(@Param("name") Serializable name, @Param("exclude") List<String> exclude, @Param("namespaceId") String namespaceId);


    /**
     * update selective plugin.
     *
     * @param namespacePluginRelDO {@linkplain NamespacePluginRelDO}
     * @return rows int
     */
    int updateSelective(NamespacePluginRelDO namespacePluginRelDO);

    /**
     * select plugin by id.
     *
     * @param ids   primary keys.
     * @param namespaceId namespace id.
     * @return {@linkplain NamespacePluginRelDO}
     */
    List<NamespacePluginVO> selectByIds(List<String> ids, String namespaceId);


    /**
     * delete plugin.
     *
     * @param ids         primary keys.
     * @param namespaceId namespace id.
     * @return rows int
     */
    int deleteByIds(List<String> ids, String namespaceId);

    /**
     * select all.
     *
     * @param namespaceId namespace id.
     * @return {@linkplain List}
     */
    List<NamespacePluginVO> selectAll(String namespaceId);

    /**
     * select all.
     *
     * @return {@linkplain List}
     */
    List<NamespacePluginVO> selectAll();

    /**
     * enable data by a list of ids.
     *
     * @param idList  a list of ids
     * @param enabled status
     * @return the count of enabled datas
     */
    int updateEnableByIdList(@Param("idList") List<String> idList, @Param("enabled") Boolean enabled);
}
