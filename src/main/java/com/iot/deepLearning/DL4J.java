package com.iot.deepLearning;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.IOException;

public class DL4J {
    private int numInputNodes = 12; // x, y, z轴各4个数据
    private int numOutputNodes = 2; // 轴承是否有故障
    private int numHiddenNodes = 20; // 隐藏层节点数量

    public MultiLayerConfiguration createModel() {
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .weightInit(WeightInit.XAVIER)
                .activation(Activation.RELU)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .updater(new Sgd(0.05))
                .list()
                .layer(0, new DenseLayer.Builder().nIn(numInputNodes).nOut(numHiddenNodes).build())
                .layer(1, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes).build())
                .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .activation(Activation.SOFTMAX).nIn(numHiddenNodes).nOut(numOutputNodes).build())
                .build();

        return conf;
    }

    // 添加训练和预测方法...

    // 训练模型的方法
    public void trainModel(MultiLayerNetwork model, DataSetIterator trainData) {
        model.fit(trainData);
    }

    // 保存模型的方法
    /*public void saveModel(MultiLayerNetwork model, String filePath) throws IOException, IOException {
        ModelSerializer.writeModel(model, filePath, true);
    }*/
    /*
    public void saveModel(MultiLayerNetwork model) throws IOException {
    String filePath = this.getClass().getResource("").getPath() + "model.zip";
    ModelSerializer.writeModel(model, filePath, true);
    }
    this.getClass().getResource("").getPath()将返回DL4J.java类的路径，然后我们添加model.zip作为模型的文件名。
    这将在与DL4J.java相同的目录中创建一个名为model.zip的文件来保存模型。
    */


    /*this.getClass().getResource("/com/iot/model").getPath()将返回com.iot.model包的路径，然后我们添加/model.zip
    作为模型的文件名。这将在com.iot.model包下创建一个名为model.zip的文件来保存模型。 */
    public void saveModel(MultiLayerNetwork model) throws IOException {
        String filePath = this.getClass().getResource("/com/iot/model").getPath() + "/model.zip";
        ModelSerializer.writeModel(model, filePath, true);
    }


    // 使用模型进行预测的方法
    public INDArray predict(MultiLayerNetwork model, INDArray testData) {
        return model.output(testData);
    }
}