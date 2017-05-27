package com.virtusa.gto.figaro.plugin.mvn;

import com.virtusa.gto.figaro.Figaro;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * @goal run
 */
public class MavenPlugin extends AbstractMojo {
    
    /**
     * @parameter expression="${run.env}"
     */
    private String env;

    public void execute() throws MojoExecutionException {
        
        getLog().info("");
        getLog().info("Running Figaro...");
        
        Figaro.execute(env);
        
    }
}
