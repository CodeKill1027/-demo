package com.example.Springcloud.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


/**
 * @author yzj
 */
@Configuration
public class NacosWeightedRule extends AbstractLoadBalancerRule {
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig)
    { }

    @Override
     public Server choose(Object o)
        {
            ILoadBalancer loadBalancer = this.getLoadBalancer();
        BaseLoadBalancer baseLoadBalancer = (BaseLoadBalancer) loadBalancer;
       String name = baseLoadBalancer.getName();
         NamingService namingService = nacosDiscoveryProperties.namingServiceInstance( ); try {
       Instance instance = namingService.selectOneHealthyInstance(name);
         return new NacosServer(instance); }
        catch (NacosException e) {
             e.printStackTrace();
             return null;
         }

        }

}