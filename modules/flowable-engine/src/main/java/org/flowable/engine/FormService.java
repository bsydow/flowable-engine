/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.flowable.engine;

import java.util.Map;

import org.flowable.engine.form.StartFormData;
import org.flowable.engine.form.TaskFormData;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.engine.task.Task;
import org.flowable.engine.task.TaskQuery;

/**
 * Access to form data and rendered forms for starting new process instances and completing tasks.
 * 
 * @author Tom Baeyens
 * @author Falko Menge (camunda)
 */
public interface FormService {

    /**
     * Retrieves all data necessary for rendering a form to start a new process instance. This can be used to perform rendering of the forms outside of the process engine.
     */
    StartFormData getStartFormData(String processDefinitionId);

    /**
     * Rendered form generated by the default build-in form engine for starting a new process instance.
     */
    Object getRenderedStartForm(String processDefinitionId);

    /**
     * Rendered form generated by the given build-in form engine for starting a new process instance.
     */
    Object getRenderedStartForm(String processDefinitionId, String formEngineName);

    /**
     * Start a new process instance with the user data that was entered as properties in a start form.
     */
    ProcessInstance submitStartFormData(String processDefinitionId, Map<String, String> properties);

    /**
     * Start a new process instance with the user data that was entered as properties in a start form.
     * 
     * A business key can be provided to associate the process instance with a certain identifier that has a clear business meaning. For example in an order process, the business key could be an order
     * id. This business key can then be used to easily look up that process instance , see {@link ProcessInstanceQuery#processInstanceBusinessKey(String)}. Providing such a business key is definitely
     * a best practice.
     * 
     * @param processDefinitionId
     *            the id of the process definition, cannot be null.
     * @param businessKey
     *            a key that identifies the process instance and can be used to retrieve the process instance later via the query API.
     * @param properties
     *            the properties to pass, can be null.
     */
    ProcessInstance submitStartFormData(String processDefinitionId, String businessKey, Map<String, String> properties);

    /**
     * Retrieves all data necessary for rendering a form to complete a task. This can be used to perform rendering of the forms outside of the process engine.
     */
    TaskFormData getTaskFormData(String taskId);

    /**
     * Rendered form generated by the default build-in form engine for completing a task.
     */
    Object getRenderedTaskForm(String taskId);

    /**
     * Rendered form generated by the given build-in form engine for completing a task.
     */
    Object getRenderedTaskForm(String taskId, String formEngineName);

    /**
     * Completes a task with the user data that was entered as properties in a task form.
     */
    void submitTaskFormData(String taskId, Map<String, String> properties);

    /** Save the data that was entered as properties in a task form. */
    void saveFormData(String taskId, Map<String, String> properties);

    /**
     * Retrieves a user defined reference to a start form.
     * 
     * In the Explorer app, it is assumed that the form key specifies a resource in the deployment, which is the template for the form. But users are free to use this property differently.
     */
    String getStartFormKey(String processDefinitionId);

    /**
     * Retrieves a user defined reference to a task form.
     * 
     * In the Explorer app, it is assumed that the form key specifies a resource in the deployment, which is the template for the form. But users are free to use this property differently.
     * 
     * Both arguments can be obtained from {@link Task} instances returned by any {@link TaskQuery}.
     */
    String getTaskFormKey(String processDefinitionId, String taskDefinitionKey);

}
